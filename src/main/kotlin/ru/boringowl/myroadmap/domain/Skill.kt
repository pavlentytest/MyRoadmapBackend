package ru.boringowl.myroadmap.domain

import java.util.*


class Skill {
    var skillId: UUID? = null
    var skillName: String = ""
    var necessity: Int = 0
    var route: Route? = null
}
