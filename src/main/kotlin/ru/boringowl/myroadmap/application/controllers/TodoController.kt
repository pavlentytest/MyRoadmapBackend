package ru.boringowl.myroadmap.application.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.boringowl.myroadmap.application.dto.ExcepUtils
import ru.boringowl.myroadmap.application.dto.ListResponse
import ru.boringowl.myroadmap.application.services.TodoService
import ru.boringowl.myroadmap.domain.Todo
import ru.boringowl.myroadmap.infrastructure.security.JwtUtils
import java.util.*

@RestController
@RequestMapping("api/todo")
class TodoController(val service: TodoService, val jwtUtils: JwtUtils) {

    @PostMapping("/{id}")
    fun add(
        @RequestHeader("Authorization") token: String,
        @PathVariable id: Int,
        @RequestParam name: String,
    ): Todo? {
        try {
            val username = jwtUtils.extractUsername(token.removePrefix("Bearer "))
             return service.addByRoute(id, name, username)
        } catch (e: IllegalArgumentException) {
            throw ExcepUtils.custom(e.message!!)
        } catch (e: Exception) {
            throw ExcepUtils.unauthorized
        }
    }
    @RequestMapping( method = [RequestMethod.PATCH, RequestMethod.PUT])
    fun update(
        @RequestHeader("Authorization") token: String,
        @RequestBody dto: Todo)
    : Todo? {
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
    ): ResponseEntity<String> {
        try {
            service.delete(id)
            return ResponseEntity.ok("Запись удалена")
        } catch (e: Exception) {
            throw ExcepUtils.notFound
        }
    }


    @GetMapping
    fun get(@RequestHeader("Authorization") token: String): ListResponse<Todo> {
        val username = jwtUtils.extractUsername(token.removePrefix("Bearer "))
        return ListResponse(service.get(username))
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): Todo =
        service.get(id) ?: throw ExcepUtils.notFound

}
