package ru.boringowl.myroadmap.application.controllers

import org.springframework.web.bind.annotation.*
import ru.boringowl.myroadmap.application.dto.ExcepUtils
import ru.boringowl.myroadmap.domain.Skill
import ru.boringowl.myroadmap.infrastructure.schedulers.RoutesScheduler
import ru.boringowl.myroadmap.infrastructure.schedulers.SkillsScheduler

@RestController
@RequestMapping("dev")
class ExperimentsContr(val routesScheduler: RoutesScheduler, val skillsScheduler: SkillsScheduler) {
    @GetMapping("1")
    fun route() {
        routesScheduler.updateRouteInfo()
    }
    @GetMapping("2")
    fun skill() {
        skillsScheduler.updateSkills()
    }
}
