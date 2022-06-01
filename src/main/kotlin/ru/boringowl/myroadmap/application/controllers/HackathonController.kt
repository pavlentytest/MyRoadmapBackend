package ru.boringowl.myroadmap.application.controllers

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.boringowl.myroadmap.application.dto.ExcepUtils
import ru.boringowl.myroadmap.application.dto.ListResponse
import ru.boringowl.myroadmap.application.dto.PagedResponse
import ru.boringowl.myroadmap.application.services.HackathonService
import ru.boringowl.myroadmap.domain.Hackathon
import java.util.*

@RestController
@RequestMapping("api/hack")
class HackathonController(val service: HackathonService) {
    @GetMapping
    fun get(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") limit: Int
    ): Page<Hackathon> =
        service.get(PageRequest.of(page, limit))

    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): Hackathon =
        service.get(id) ?: throw ExcepUtils.notFound

}
