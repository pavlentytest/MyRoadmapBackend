package ru.boringowl.myroadmap.application.controllers

import org.springframework.web.bind.annotation.*
import ru.boringowl.myroadmap.infrastructure.schedulers.HackathonScheduler
import ru.boringowl.myroadmap.infrastructure.schedulers.RoutesScheduler
import ru.boringowl.myroadmap.infrastructure.schedulers.SkillsScheduler

@RestController
@RequestMapping("dev")
class ExperimentsController(
    val routesScheduler: RoutesScheduler,
    val hackathonScheduler: HackathonScheduler,
    val skillsScheduler: SkillsScheduler
    ) {
    @GetMapping("1")
    fun route() {
        routesScheduler.updateRouteInfo()
    }
    @GetMapping("2")
    fun skill() {
        skillsScheduler.updateSkills()
    }
    @GetMapping("3")
    fun hack() {
        hackathonScheduler.updateHackathons()
    }
}
