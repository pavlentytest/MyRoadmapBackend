package ru.boringowl.myroadmap.infrastructure.schedulers

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.services.BookPostService
import ru.boringowl.myroadmap.application.services.RouteService
import ru.boringowl.myroadmap.domain.BookInfo
import ru.boringowl.myroadmap.domain.BookPost
import ru.boringowl.myroadmap.infrastructure.config.AppCoroutineScope
import ru.boringowl.myroadmap.infrastructure.utils.Constants

@Service
class BooksScheduler(
    val routeService: RouteService,
    val bookPostService: BookPostService,
    val scope: AppCoroutineScope
) {

    @Value("\${vk.access_token}")
    val token = ""
    val domain = "proglib"
    val count = 100

    @Scheduled(
        fixedDelay  = Constants.millisecondsIn7Days
    )
    fun updateSkills() {
        scope.launch {
            start()
        }
    }
    fun start() {
        println(token)
        routeService.get().forEach {r ->
            getBooks(r.routeName).forEach {
                if(!bookPostService.isExists(it.description))
                    bookPostService.add(it)
            }

        }

    }
    private fun getBooks(route: String, offset: Int = 0): ArrayList<BookPost> {
        println("$route: $offset")
        val books = arrayListOf<BookPost>()
        var responseCount: Int? = null
        val link = "https://api.vk.com/method/wall.search?" +
                "domain=$domain&" +
                "query=%23book@proglib%20$route&" +
                "count=$count&" +
                "offset=$offset&" +
                "access_token=$token&" +
                "v=5.131"
        when (val result = link.httpGet().responseObject<VkResponse>().third) {
            is Result.Failure -> {
                println(result.error.response.responseMessage)
            }
            is Result.Success -> {
                val res = result.get()
                responseCount = res.response?.count
                res.response?.items?.forEachIndexed {i, post ->
                    val attachments = arrayListOf<BookInfo>()
                    post.attachments.filter { it.type == "doc" }.forEach {att ->
                        att.doc?.let { attachments.add(BookInfo(it.url, it.title, it.size)) }
                    }
                    books.add(BookPost(description = post.text, books = attachments))
                }
            }
        }
        if (responseCount != null && responseCount > offset + count)
            books.addAll(getBooks(route, offset + 100))
        return books
    }
}

class VkResponse {
    val response: VkPostsList? = null
}
class VkPostsList {
    val count: Int = 0
    val items: List<VkPost> = listOf()
}
class VkPost {
    val text: String = ""
    val attachments: List<Attachment> = listOf()
}

class Attachment {
    val type: String = ""
    val doc: Document? = null
}
class Document {
    val title: String = ""
    val ext: String = ""
    val url: String = ""
    val size: Int = 0
}

