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
    @RequestMapping(path= ["/progress/{id}"], method = [RequestMethod.PATCH, RequestMethod.PUT])
    fun setState(
        @RequestHeader("Authorization") token: String,
        @PathVariable id: UUID,
        @RequestParam progress: Int
    ): SkillTodo? {
        try {
            return service.update(id, progress)
        } catch (e: Exception) {
            throw ExcepUtils.notFound
        }
    }
    @DeleteMapping("/{id}")
    fun delete(
        @RequestHeader("Authorization") token: String,
        @PathVariable id: UUID
    ): ResponseEntity<String> {
        try {
            service.delete(id)
            return ResponseEntity.ok("Запись удалена")
        } catch (e: Exception) {
            throw ExcepUtils.notFound
        }
    }


    @GetMapping("/todo/{todoId}")
    fun getByTodo(@PathVariable todoId: UUID): ListResponse<SkillTodo> =
        ListResponse(service.getByTodo(todoId))

    @GetMapping("/{skillTodoId}")
    fun get(
        @PathVariable skillTodoId: UUID,
    ): SkillTodo =
        service.get(skillTodoId) ?: throw ExcepUtils.notFound

}
