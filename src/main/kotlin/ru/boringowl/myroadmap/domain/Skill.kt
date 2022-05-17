package ru.boringowl.myroadmap.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
class Skill {
    var skillId: UUID? = null
    var skillName: String = ""
    var necessity: Int = 0
    var route: Route? = null
}
