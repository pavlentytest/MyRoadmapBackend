package ru.boringowl.myroadmap.application.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.boringowl.myroadmap.application.dto.ExcepUtils
import ru.boringowl.myroadmap.application.dto.ListResponse
import ru.boringowl.myroadmap.application.services.VacancyService
import ru.boringowl.myroadmap.domain.Vacancy

@RestController
@RequestMapping("api/vacancy")
class VacancyController(val service: VacancyService) {

    @PostMapping
    fun add(
        @RequestHeader("Authorization") token: String,
        @RequestBody dto: Vacancy
    ): Vacancy? {
        try {
            return service.add(dto)
        } catch (e: Exception) {
            throw ExcepUtils.exists
        }
    }
    @RequestMapping( method = [RequestMethod.PATCH, RequestMethod.PUT])
    fun update(
        @RequestHeader("Authorization") token: String,
        @RequestBody dto: Vacancy)
    : Vacancy? {
        try {
            return service.update(dto)
        } catch (e: Exception) {
            throw ExcepUtils.notFound
        }

    }
    @DeleteMapping("/{id}")
    fun deletePattern(
        @RequestHeader("Authorization") token: String,
        @PathVariable id: Int
    ): ResponseEntity<String> {
        try {
            service.delete(id)
            return ResponseEntity.ok("Запись удалена")
        } catch (e: Exception) {
            throw ExcepUtils.notFound
        }
    }


    @GetMapping
    fun get(): ListResponse<Vacancy> =
        ListResponse(service.get())

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): Vacancy =
        service.get(id) ?: throw ExcepUtils.notFound

}
