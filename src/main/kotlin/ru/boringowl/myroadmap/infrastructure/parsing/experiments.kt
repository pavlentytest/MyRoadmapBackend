package ru.boringowl.myroadmap.infrastructure.parsing

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import ru.boringowl.myroadmap.application.persistence.Routes
import ru.boringowl.myroadmap.infrastructure.utils.getSimilarityAndKey


object Parser {
    fun getVacanciesForJob(job: String): Map<String, Int> {
        var skills = hashMapOf<String, Int>()
        val pages = 20
        val perPage = 100
        for (i in 1..pages) {
            val link = "https://api.hh.ru/vacancies?text=${job}&per_page=$perPage&page=$i"
            val (_, _, result) = link
                .httpGet()
                .responseObject<VacancyResponse>()
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    println(ex)
                }
                is Result.Success -> {
                    val data = result.get()
                    data.items.forEach { vac ->
                        val vacancySkills = getVacancySkills(vac.id)
                        vacancySkills.forEach {
                            skills[it] = 1 + (skills[it] ?: 0)
                        }
                    }
                }
            }
        }
        val res = hashMapOf<String, Int>()
        skills.forEach {
            val simPair = res.getSimilarityAndKey(it.key)
            if (simPair.first > 0.7 && !exclusionWords.contains(it.key)) {
                res[simPair.second!!] = it.value + (res[simPair.second!!] ?: 0)
            } else {
                res[it.key] = it.value
            }
        }
        return res.toList().filter { (_, v) -> v > 1 }.sortedBy { (k, v) -> v }
            .map { (k, v) -> Pair(k, v * 100 / (pages * perPage)) }.reversed().toMap()
    }

    private fun getVacancySkills(id: String): List<String> {
        val link = "https://api.hh.ru/vacancies/$id"
        val (request, response, result) = link
            .httpGet()
            .responseObject<Vacancy>()
        return when (result) {
            is Result.Failure -> {
                listOf()
            }
            is Result.Success -> {
                val data = result.get()
                val res = data.skills?.map {
                    it.name.lowercase()
                }?.filter {
                    it.split(" ").size < 3
                }

                res ?: listOf()
            }
        }
    }


    fun getVacancyAndResumeCount(job: String): Pair<Int, Int> {
        println(job)
        val vacancyLink =
            "https://hh.ru/search/vacancy?search_field=name&search_field=company_name&search_field=description&text=$job"
        val resumeLink =
            "https://hh.ru/search/resume?text=$job&exp_period=all_time&logic=normal&pos=full_text&fromSearchLine=false"
        val resumeRegex = Regex("резюме у.*?соискател")
        val vacancyRegex = Regex("section-3\">[\\d&nbsp;  ]*<.*?>ваканси")
        val vacancies = getCount(vacancyLink, vacancyRegex)
        val resumes = getCount(resumeLink, resumeRegex)

        return Pair(vacancies, resumes)
    }

    fun getCount(link: String, regex: Regex): Int {
        val (_, _, result) = link
            .httpGet()
            .responseString()
        return when (result) {
            is Result.Failure -> {
                println(result.getException())
                return -1
            }
            is Result.Success -> {
                val data = result.get()
                val res = regex.find(data)!!.value
                println(res)
                res.replace("section-3", "").filter { it.isDigit() }.toInt()
            }
        }
    }
}

fun main() {
//    val s = Parser.getVacanciesForJob(Routes.ANDROID.name)
//    s.forEach { (t, u) ->
//        println("$t : $u%")
//    }
    val values = hashMapOf<String, Double>()
    listOf("игры").forEach {
        val s = Parser.getVacancyAndResumeCount(it)
        println(s)
        values[it] = s.second/(s.first*10).toDouble()
    }
    values.toList().sortedBy { it.second }.toMap().forEach { (t, u) ->
        println("$t : ${String.format("%.2f", u)} соискателя на вакансию")
    }
}


val exclusionWords = arrayListOf(
    "android", "ios", "swift",
)
