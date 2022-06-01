package ru.boringowl.myroadmap.domain

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
class BookPost(
    var bookPostId: UUID? = null,
    var description: String = "",
    var books: List<BookInfo> = listOf()
)

class BookInfo (
    var url: String = "",
    var filename: String = "",
    var size: Int = 0
)
