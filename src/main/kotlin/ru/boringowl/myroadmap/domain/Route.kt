package ru.boringowl.myroadmap.domain



class Route(var routeId: Int? = null, var routeName: String = "", var routeDescription: String = "") {
    var resumesCount: Int = 0
    var vacanciesCount: Int = 0
}
