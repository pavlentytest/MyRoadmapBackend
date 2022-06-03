package ru.boringowl.myroadmap.domain


import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class Route(var routeId: Int? = null, var routeName: String = "", var routeDescription: String = "") {
    var resumesCount: Int = 0
    var vacanciesCount: Int = 0
}
