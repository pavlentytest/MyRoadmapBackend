package ru.boringowl.myroadmap.application.dto

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

object ExcepUtils {

    val notFound = ResponseStatusException(HttpStatus.NOT_FOUND, "Запись не найдена")
    val forbidden = ResponseStatusException(HttpStatus.FORBIDDEN, "Нет доступа")
    val unauthorized = ResponseStatusException(HttpStatus.UNAUTHORIZED, "Не авторизован")
    val exists = ResponseStatusException(HttpStatus.BAD_REQUEST, "Запись с таким id уже существует")
    val userExists = ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователь с таким именем уже существует")
}
