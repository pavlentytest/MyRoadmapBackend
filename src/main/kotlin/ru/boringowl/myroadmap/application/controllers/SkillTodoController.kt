package ru.boringowl.myroadmap.application.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.boringowl.myroadmap.application.dto.ExcepUtils
import ru.boringowl.myroadmap.application.dto.ListResponse
import ru.boringowl.myroadmap.application.services.SkillTodoService
import ru.boringowl.myroadmap.domain.SkillTodo
import java.util.*

@RestController
@RequestMapping("api/st")
class SkillTodoController(val service: SkillTodoService) {

    @PostMapping
    fun add(
        @RequestHeader("Authorization") token: String,
        @RequestBody dto: SkillTodo
    ): SkillTodo? {
        try {
            return service.add(dto)
        } catch (e: Exception) {
            throw ExcepUtils.exists
        }
    }
    @RequestMapping( method = [RequestMethod.PATCH, RequestMethod.PUT])
    fun update(
        @RequestHeader("Authorization") token: String,
        @RequestBody dto: SkillTodo
    ): SkillTodo? {
        try {
            return service.update(dto)
        } catch (e: Exception) {
            throw ExcepUtils.notFound
        }
    }
    @DeleteMapping("/{skillId}/{todoId}")
    fun delete(
        @RequestHeader("Authorization") token: String,
        @PathVariable skillId: UUID,
        @PathVariable todoId: UUID
    ): ResponseEntity<String> {
        try {
            service.delete(skillId, todoId)
            return ResponseEntity.ok("Запись удалена")
        } catch (e: Exception) {
            throw ExcepUtils.notFound
        }
    }


    @GetMapping
    fun get(): ListResponse<SkillTodo> =
        ListResponse(service.get())

    @GetMapping("/{skillId}/{todoId}")
    fun get(
        @PathVariable skillId: UUID,
        @PathVariable todoId: UUID
    ): SkillTodo =
        service.get(skillId, todoId) ?: throw ExcepUtils.notFound

}
