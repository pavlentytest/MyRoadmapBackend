package ru.boringowl.myroadmap.domain

import java.util.*

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
