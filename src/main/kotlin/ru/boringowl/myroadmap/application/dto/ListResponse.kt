package ru.boringowl.myroadmap.application.dto

class ListResponse<T>(val items: List<T>)

class PagedResponse<T>(val items: List<T>, val prevPage: Int?, val nextPage: Int?) {
    constructor(triple: Triple<List<T>, Int?, Int?>) : this(triple.first, triple.second, triple.third)
}
