package ru.boringowl.myroadmap.infrastructure.utils

import kotlin.math.min

fun <T> List<T>.paginate(page: Int, perPage: Int): Triple<List<T>, Int?, Int?> {
    var maxPage = size/perPage
    if (maxPage * perPage < size)
        maxPage++
    require(page in 1..maxPage) {"Неверно задана страница"}
    require(perPage > 0) {"Количество элементов на странице должно быть больше 0"}
    val first = (page - 1) * perPage
    val last = min(first + perPage, size)
    val lst = if (first > last) this else subList(first, last)
    val prev = if (page < 2) null else page - 1
    val next = if (page >= maxPage) null else page + 1
    return Triple(lst, prev, next)
}
