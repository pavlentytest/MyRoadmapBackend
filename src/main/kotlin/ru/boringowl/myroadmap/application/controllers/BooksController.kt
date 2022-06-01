package ru.boringowl.myroadmap.application.controllers

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*
import ru.boringowl.myroadmap.application.dto.ListResponse
import ru.boringowl.myroadmap.application.services.BookPostService
import ru.boringowl.myroadmap.domain.BookPost

@RestController
@RequestMapping("api/books")
class BooksController(val service: BookPostService) {


    @GetMapping("/{id}")
    fun get(@PathVariable id: Int,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "20") limit: Int
    ): ListResponse<BookPost> =
        ListResponse(service.getByRoute(id, PageRequest.of(page, limit)))

    @GetMapping()
    fun get(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") limit: Int
    ): ListResponse<BookPost> =
        ListResponse(service.get(PageRequest.of(page, limit)))

}
