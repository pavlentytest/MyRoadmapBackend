package ru.boringowl.myroadmap.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*


import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class Todo() {
    var todoId: UUID? = null
    var header = ""
    @JsonIgnore
    var user: User? = null
    var skills: List<SkillTodo>? = listOf()
}
