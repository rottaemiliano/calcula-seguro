package com.example.entrypoint.handler

import com.example.entrypoint.handler.exception.GenericException
import com.example.entrypoint.handler.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handleAll(ex: Exception): ResponseEntity<Any> {
        return ResponseEntity(
            ErrorResponse("Internal error. Something did not work well."),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(GenericException::class)
    fun handleTooManyRequestsException(ex: GenericException): ResponseEntity<Any> {
        return ResponseEntity(ErrorResponse(ex.message), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
