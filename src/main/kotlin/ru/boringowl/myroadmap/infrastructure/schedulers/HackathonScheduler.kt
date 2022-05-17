package ru.boringowl.myroadmap.infrastructure.schedulers

import org.jsoup.Jsoup
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.services.HackathonService
import ru.boringowl.myroadmap.domain.Hackathon
import ru.boringowl.myroadmap.infrastructure.utils.Constants

@Service
class HackathonScheduler(val service: HackathonService) {
    //хакатоны.рф
    private val link = "https://www.xn--80aa3anexr8c.xn--p1ai/"
    private val splitSym = "#:#:#"
    private val hackColumnClass = "t776__col"
    private val hackTitleClass = "t776__title"
    private val hackDescClass = "js-product-link"
    private val hackLinkClass = "t776__descr"
    private val hackImgClass = "t776__bgimg"

    private val words  = mapOf(
        "Хакатон:" to "date",
        "Прием заявок:" to "registration",
        "Технологический фокус хакатона:" to "focus",
        "Технологический_фокус_хакатона:" to "focus",
        "Призовой фонд:" to "prize",
        "Целевая аудитория:" to "routes",
        "Условия участия:" to "terms",
        "Организаторы:" to "organization",
        "Организатор:" to "organization",
    )

    @Scheduled(
        initialDelay = Constants.millisecondsInDay,
        fixedDelay  = Constants.millisecondsIn7Days
    )
    fun updateHackathons() {
        val doc = Jsoup.connect(link).get()
        doc.getElementsByClass(hackColumnClass).forEach {el ->
            val title = el.getElementsByClass(hackTitleClass).first().text()
            val source = el.getElementsByClass(hackDescClass).first().attr("href")
            val img = try {
                el.getElementsByClass(hackImgClass).first().attr("data-original")
            } catch (e: Exception) {
                null
            }
            var description = el.getElementsByClass(hackLinkClass).first().text()
            words.forEach { (k, _) ->
                description = description.replace(k, "$splitSym$k$splitSym")
            }
            description = description.replace("_фокус_", " фокус ")
            val data = hashMapOf<String, String>()
            val d = description.split(splitSym)
            description = d[0]
            d.forEachIndexed { j, w ->
                if (words.keys.contains(w))
                    data[words[w]!!] = d[j+1]
            }
            save(title, description, source, img, data)
        }
    }

    private fun save(title: String, description: String, source: String, image: String?, data: Map<String, String>) {
        val h = Hackathon().apply {
            this.hackTitle = title
            this.hackDescription = description
            this.source = source
            this.date = data["date"]
            this.registration = data["registration"]
            this.focus = data["focus"]
            this.prize = data["prize"]
            this.routes = data["routes"]
            this.terms = data["terms"]
            this.organization = data["organization"]
            this.imageUrl = image
        }
        if (!service.existsBySourceAndDate(h.source, h.date))
            service.add(h)
    }
}

