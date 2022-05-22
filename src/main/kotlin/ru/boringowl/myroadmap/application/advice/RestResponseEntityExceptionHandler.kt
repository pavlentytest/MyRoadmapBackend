package ru.boringowl.myroadmap.application.advice

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [ResponseStatusException::class])
    protected fun handleError(
        ex: ResponseStatusException?, request: WebRequest?
    ): ResponseEntity<Any> {
        return handleExceptionInternal(
            ex!!, ex.reason,
            HttpHeaders(), ex.status, request!!
        )
    }
}
