package ru.boringowl.myroadmap.application.controllers

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import ru.boringowl.myroadmap.application.dto.ExcepUtils
import ru.boringowl.myroadmap.application.services.BookPostService
import ru.boringowl.myroadmap.domain.BookPost
import java.util.*

@RestController
@RequestMapping("api/books")
class BooksController(val service: BookPostService) {
    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): BookPost =
        service.get(id) ?: throw ExcepUtils.notFound

    @GetMapping("/route/{id}")
    fun get(
        @PathVariable id: Int,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") limit: Int,
        @RequestParam(defaultValue = "") query: String,
    ): Page<BookPost> =
        service.getByQuery(id, PageRequest.of(page, limit), query)

}
