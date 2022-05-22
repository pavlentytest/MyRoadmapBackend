package ru.boringowl.myroadmap.application.dto

import com.google.gson.Gson
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

object ExcepUtils {

    val notFound = ResponseStatusException(HttpStatus.NOT_FOUND, "Запись не найдена")
    val forbidden = ResponseStatusException(HttpStatus.FORBIDDEN, "Нет доступа")
    val unauthorized = ResponseStatusException(HttpStatus.UNAUTHORIZED, "Токен не валиден")
    val exists = ResponseStatusException(HttpStatus.BAD_REQUEST, "Запись с таким id уже присутствует")
    fun custom(message: String) = ResponseStatusException(HttpStatus.BAD_REQUEST, message)
}

class ExcepResponse(val status: HttpStatus, val message: String) {
    constructor(exception: ResponseStatusException): this(exception.status, exception.reason!!)
    fun toJson(): String = Gson().toJson(this)
}
