package ru.boringowl.myroadmap.infrastructure.utils

import kotlin.math.min

fun <T> List<T>.paginate(page: Int, perPage: Int): List<T> {
    val first = (page - 1) * perPage
    val last = min(first + perPage, this.size-1)
    return if (first >= last) this else this.subList(first, last)
}
