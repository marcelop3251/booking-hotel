package com.tcc.bookinghotel.application.routers

import com.newrelic.api.agent.NewRelic
import com.tcc.bookinghotel.application.dto.HttpErrorResponse
import com.tcc.bookinghotel.domain.exception.BaseException
import com.tcc.bookinghotel.domain.exception.CustomerRegistryException
import com.tcc.bookinghotel.domain.exception.TypeException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class ExceptionHandler (
    private val exceptionHandlerSpecifications: Map<TypeException, ExceptionHandlerSpecification>
) {

    private val log = LoggerFactory.getLogger(javaClass)

    suspend fun handler(e: Throwable, serverRequest: ServerRequest): ServerResponse {
        log.error("error {}", e)
        NewRelic.noticeError(e)
        val (httpResponse, httpStatus) = when (e) {
            is BaseException -> {
                this.exceptionHandlerSpecifications[e.type]?.handler(e) ?: internalServerError()
            }
            else -> {
                internalServerError()
            }
        }
        return ServerResponse.status(httpStatus).bodyValueAndAwait(httpResponse)
    }

    private fun internalServerError() = HttpErrorResponse(
        "INTERNAL_SERVER_ERROR",
        "Error not identified"
    ) to HttpStatus.INTERNAL_SERVER_ERROR
}