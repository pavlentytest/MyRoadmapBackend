package ru.boringowl.myroadmap.application.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.boringowl.myroadmap.application.dto.ListResponse
import ru.boringowl.myroadmap.application.services.BookPostService
import ru.boringowl.myroadmap.domain.BookPost

@RestController
@RequestMapping("api/books")
class BooksController(val service: BookPostService) {


    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ListResponse<BookPost> =
        ListResponse(service.getByRoute(id))

    @GetMapping()
    fun get(): ListResponse<BookPost> =
        ListResponse(service.get())

}
