package com.example.material.config

import com.example.material.dto.ErrorResponse
import com.example.material.exception.CustomException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

/**
 * CustomExceptionHandler handles exceptions and returns custom error response and http status code.
 */
@Component
class CustomExceptionHandler : ErrorWebExceptionHandler {

    private val objectMapper = ObjectMapper()
    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {
        val response = exchange.response
        if (response.isCommitted) {
            return Mono.error(ex)
        }

        response.headers.contentType = MediaType.APPLICATION_JSON
        if (ex is CustomException) {
            val errorResponse = ErrorResponse(ex.errorCode.toString(), "Exception happened: ${ex.message}")
            val buffer = response.bufferFactory().wrap(objectMapper.writeValueAsBytes(errorResponse))
            response.statusCode = HttpStatus.valueOf(ex.errorCode)  // find HttpStatusCode from http error code
            return response.writeWith(Mono.just(buffer))
        } else {
            return Mono.error(ex)
        }
    }
}