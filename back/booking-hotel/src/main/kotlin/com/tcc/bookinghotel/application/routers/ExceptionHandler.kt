package com.tcc.bookinghotel.application.routers

import com.newrelic.api.agent.NewRelic
import com.tcc.bookinghotel.application.dto.HttpErrorResponse
import com.tcc.bookinghotel.domain.exception.BaseException
import com.tcc.bookinghotel.domain.exception.CustomerRegistryException
import com.tcc.bookinghotel.domain.exception.TypeException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

object ExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    suspend fun handler(e: Throwable, serverRequest: ServerRequest): ServerResponse {
        log.error("error {}", e)
        NewRelic.noticeError(e)
        val (httpResponse, status) = when(e) {
            is BaseException -> {
                when (e.type) {
                    TypeException.CUSTOMER_REGISTRATION, TypeException.COMPANY_REGISTRATION -> {
                        HttpErrorResponse(e.type.toString(), e.message) to HttpStatus.UNPROCESSABLE_ENTITY
                    }
                    else -> {
                        HttpErrorResponse("INTERNAL_SERVER_ERROR", "Error not identified") to HttpStatus.INTERNAL_SERVER_ERROR
                    }
                }
            }
            else -> {
                HttpErrorResponse("INTERNAL_SERVER_ERROR", "Error not identified") to HttpStatus.INTERNAL_SERVER_ERROR
            }
        }
        return ServerResponse.status(status).bodyValueAndAwait(httpResponse)
    }
}