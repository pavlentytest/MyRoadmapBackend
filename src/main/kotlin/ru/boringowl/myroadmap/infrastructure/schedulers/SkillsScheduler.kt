package ru.boringowl.myroadmap.infrastructure.schedulers

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.coroutines.launch
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.services.RouteService
import ru.boringowl.myroadmap.application.services.SkillService
import ru.boringowl.myroadmap.domain.Skill
import ru.boringowl.myroadmap.infrastructure.config.AppCoroutineScope
import ru.boringowl.myroadmap.infrastructure.parsing.Vacancy
import ru.boringowl.myroadmap.infrastructure.parsing.VacancyResponse
import ru.boringowl.myroadmap.infrastructure.utils.Constants
import ru.boringowl.myroadmap.infrastructure.utils.getMostSimilar
import kotlin.math.roundToInt

@Service
class SkillsScheduler(val routeService: RouteService,
                      val skillService: SkillService,
                      val scope: AppCoroutineScope
) {


    @Scheduled(
        fixedDelay  = Constants.millisecondsIn7Days
    )
    fun updateSkills() {
        scope.launch {
            start()
        }
    }

    private fun start() {
        val routes = routeService.get()
        routes.forEach { route ->
            val skills = getSkillsForRoute(route.routeName)
            skills.forEach {
                skillService.saveByNameAndRoute(
                    Skill().apply {
                        this.route = route
                        this.skillName = it.key
                        this.necessity = it.value
                    }
                )
            }
        }
    }

    private fun getSkillsForRoute(route: String): Map<String, Int> {
        val skills = hashMapOf<String, Int>()
        val pages = 1
        val perPage = 100
        for (i in 1..pages) {
            val link = "https://api.hh.ru/vacancies?text=${route}&per_page=$perPage&page=$i"
            val (_, _, result) = link.httpGet().responseObject<VacancyResponse>()
            when (result) {
                is Result.Failure -> {
                    println(result.getException().toString())
                }
                is Result.Success -> {
                    result.get().items.forEach { vac ->
                        getVacancySkills(vac.id).forEach {
                            skills[it] = 1 + (skills[it] ?: 0)
                        }
                    }
                }
            }
        }
        val res = hashMapOf<String, Int>()
        skills.forEach {
            val similar = res.getMostSimilar(it.key)
            if (!similar.isNullOrBlank())
                res[similar] = it.value + (res[similar] ?: 0)
            else
                res[it.key] = it.value
        }
        val average = skills.map { it.value }.average()
        val a = res
            .toList()
            .filter { (_, v) -> v > 1 }
            .sortedBy { (_, v) -> v }
            .map { (k, v) -> Pair(k, (v.toDouble()/average*10).roundToInt()) }
            .reversed()
            .toMap()
        return a
    }

    private fun getVacancySkills(id: String): List<String> {
        val link = "https://api.hh.ru/vacancies/$id"
        return when (val result = link.httpGet().responseObject<Vacancy>().third) {
            is Result.Failure -> {
                Thread.sleep(5000)
                getVacancySkills(id)
            }
            is Result.Success -> {
                val res = result.get().skills?.map { it.name.lowercase() }?.filter { it.split(" ").size < 3 }
                res ?: listOf()
            }
        }
    }

}
