package ru.boringowl.myroadmap.application.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.boringowl.myroadmap.application.dto.ExcepUtils
import ru.boringowl.myroadmap.application.dto.ListResponse
import ru.boringowl.myroadmap.application.dto.StringResponse
import ru.boringowl.myroadmap.application.services.RouteService
import ru.boringowl.myroadmap.domain.Route

@RestController
@RequestMapping("api/route")
class RouteController(val service: RouteService) {

    @PostMapping
    fun add(
        @RequestHeader("Authorization") token: String,
        @RequestBody dto: Route
    ): Route? {
        try {
            return service.add(dto)
        } catch (e: Exception) {
            throw ExcepUtils.exists
        }
    }
    @RequestMapping( method = [RequestMethod.PATCH, RequestMethod.PUT])
    fun update(
        @RequestHeader("Authorization") token: String,
        @RequestBody dto: Route)
    : Route? {
        try {
            return service.update(dto)
        } catch (e: Exception) {
            throw ExcepUtils.notFound
        }

    }
    @DeleteMapping("/{id}")
    fun delete(
        @RequestHeader("Authorization") token: String,
        @PathVariable id: Int
    ): ResponseEntity<StringResponse> {
        try {
            service.delete(id)
            return ResponseEntity.ok(StringResponse("Запись удалена"))
        } catch (e: Exception) {
            throw ExcepUtils.notFound
        }
    }


    @GetMapping
    fun get(): ListResponse<Route> =
        ListResponse(service.get())

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): Route =
        service.get(id) ?: throw ExcepUtils.notFound

}
