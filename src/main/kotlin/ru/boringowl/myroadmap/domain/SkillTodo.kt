package ru.boringowl.myroadmap.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*


import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class SkillTodo {
    var skillTodoId: UUID? = null
    var skill: Skill? = null
    var todo: Todo? = null
    var progress: Int = 0
    var notes: String = ""
}
