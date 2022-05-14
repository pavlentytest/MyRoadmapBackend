package ru.boringowl.myroadmap.application.dto

import com.google.gson.Gson
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

object ExcepUtils {

    val notFound = ResponseStatusException(HttpStatus.NOT_FOUND, "Not found")
    val forbidden = ResponseStatusException(HttpStatus.FORBIDDEN, "No access")
    val unauthorized = ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is not passed or is not valid")
    val exists = ResponseStatusException(HttpStatus.BAD_REQUEST, "Record with this id already exists")
    val userExists = ResponseStatusException(HttpStatus.BAD_REQUEST, "User with that username already exists")

}

class ExcepResponse(val status: HttpStatus, val message: String) {
    constructor(exception: ResponseStatusException): this(exception.status, exception.reason!!)
    fun toJson(): String = Gson().toJson(this)
}
