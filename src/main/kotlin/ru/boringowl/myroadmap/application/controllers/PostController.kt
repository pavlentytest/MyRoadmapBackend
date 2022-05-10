package ru.boringowl.myroadmap.application.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.boringowl.myroadmap.application.dto.ExcepUtils
import ru.boringowl.myroadmap.application.dto.ListResponse
import ru.boringowl.myroadmap.application.services.PostService
import ru.boringowl.myroadmap.domain.Post
import java.util.*

@RestController
@RequestMapping("api/post")
class PostController(val service: PostService) {

    @PostMapping
    fun add(
        @RequestHeader("Authorization") token: String,
        @RequestBody dto: Post
    ): Post? {
        try {
            return service.add(dto)
        } catch (e: Exception) {
            throw ExcepUtils.exists
        }
    }
    @RequestMapping( method = [RequestMethod.PATCH, RequestMethod.PUT])
    fun update(
        @RequestHeader("Authorization") token: String,
        @RequestBody dto: Post)
    : Post? {
        try {
            return service.update(dto)
        } catch (e: Exception) {
            throw ExcepUtils.notFound
        }

    }
    @DeleteMapping("/{id}")
    fun deletePattern(
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
    fun get(): ListResponse<Post> =
        ListResponse(service.get())

    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): Post =
        service.get(id) ?: throw ExcepUtils.notFound

}
