package ru.boringowl.myroadmap.application.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.boringowl.myroadmap.application.dto.ExcepUtils
import ru.boringowl.myroadmap.application.dto.ListResponse
import ru.boringowl.myroadmap.application.dto.StringResponse
import ru.boringowl.myroadmap.application.services.SkillService
import ru.boringowl.myroadmap.domain.Skill
import java.util.*

@RestController
@RequestMapping("api/skill")
class SkillController(val service: SkillService) {

    @PostMapping
    fun add(
        @RequestHeader("Authorization") token: String,
        @RequestBody dto: Skill
    ): Skill? {
        try {
            return service.add(dto)
        } catch (e: Exception) {
            throw ExcepUtils.exists
        }
    }
    @RequestMapping( method = [RequestMethod.PATCH, RequestMethod.PUT])
    fun update(
        @RequestHeader("Authorization") token: String,
        @RequestBody dto: Skill)
    : Skill? {
        try {
            return service.update(dto)
        } catch (e: Exception) {
            throw ExcepUtils.notFound
        }

    }
    @DeleteMapping("/{id}")
    fun delete(
        @RequestHeader("Authorization") token: String,
        @PathVariable id: UUID
    ): ResponseEntity<StringResponse> {
        try {
            service.delete(id)
            return ResponseEntity.ok(StringResponse("Запись удалена"))
        } catch (e: Exception) {
            throw ExcepUtils.notFound
        }
    }


    @GetMapping("/route/{id}")
    fun get(@PathVariable id: Int
    ): ListResponse<Skill> =
        ListResponse(service.getByRouteId(id))

    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): Skill =
        service.get(id) ?: throw ExcepUtils.notFound

}
