package ru.boringowl.myroadmap.infrastructure.schedulers

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.coroutines.launch
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.services.RouteService
import ru.boringowl.myroadmap.infrastructure.config.AppCoroutineScope
import ru.boringowl.myroadmap.infrastructure.utils.Constants

@Service
class RoutesScheduler(val routeService: RouteService, val scope: AppCoroutineScope) {


    @Scheduled(
        initialDelay = Constants.millisecondsInDay,
        fixedDelay  = Constants.millisecondsIn7Days
    )
    fun updateRouteInfo() {
        scope.launch {
            start()
        }
    }

    private fun start() {
        val routes = routeService.get()
        routes.forEach {
            val (vacCount, resumeCount) = getVacancyAndResumeCount(it.routeName)
            it.resumesCount = resumeCount
            it.vacanciesCount = vacCount
            routeService.update(it)
        }
    }

    private fun getVacancyAndResumeCount(route: String): Pair<Int, Int> {
        val vacancyLink =
            "https://hh.ru/search/vacancy?text=$route"
        val resumeLink =
            "https://hh.ru/search/resume?" +
                    "&job_search_status=active_search" +
                    "&job_search_status=looking_for_offers" +
                    "&relocation=living_or_relocation" +
                    "&gender=unknown" +
                    "&text=$route" +
                    "&isDefaultArea=true" +
                    "&exp_period=all_time" +
                    "&logic=normal" +
                    "&pos=full_text" +
                    "&search_period=0"
        val resumeRegex = Regex("резюме у.*?соискател")
        val vacancyRegex = Regex("section-3\">(<.*?>,)?[\\d&nbsp;  ]*(<.*?>)?ваканси")
        val vacancies = getCount(vacancyLink, vacancyRegex)
        val resumes = getCount(resumeLink, resumeRegex)

        return Pair(vacancies, resumes)
    }

    private fun getCount(link: String, regex: Regex): Int {
        return when (val result = link.httpGet().responseString().third) {
            is Result.Failure -> {
                println(result.getException())
                return -1
            }
            is Result.Success -> {
                val data = result.get()
                val res = regex.find(data)!!.value
                res.replace("section-3", "").filter { it.isDigit() }.toInt()
            }
        }
    }
}
