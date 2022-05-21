package ru.boringowl.myroadmap.infrastructure.utils

fun <T> List<T>.paginate(page: Int, perPage: Int): List<T> {
    val first = (page - 1) * perPage
    val last = first + perPage
    return this.subList(first, last)
}
