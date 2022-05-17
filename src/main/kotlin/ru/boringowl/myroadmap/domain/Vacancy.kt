package ru.boringowl.myroadmap.domain

import java.util.*


import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class Vacancy {
    var vacancyId: Int? = null
    var vacancyName: String = ""
    var vacancyDescription: String = ""
    var salaryFrom: Int = 0
    var salaryTo: Int = 0
    var company: String = ""
}
