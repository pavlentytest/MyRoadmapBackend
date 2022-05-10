package ru.boringowl.myroadmap.domain

import java.time.Instant
import java.time.LocalDateTime
import java.util.*


class Post {
    var postId: UUID? = null
    var postName: String = ""
    var postDescription: String = ""
    var dateFrom: LocalDateTime = LocalDateTime.now()
    var dateTo: LocalDateTime = LocalDateTime.now()
    var source: String = ""
    var isHackathon: Boolean = false
}
