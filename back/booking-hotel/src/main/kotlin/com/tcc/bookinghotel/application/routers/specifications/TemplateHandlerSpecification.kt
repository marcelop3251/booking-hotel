package com.tcc.bookinghotel.application.routers.specifications

import com.tcc.bookinghotel.application.dto.HttpErrorResponse
import com.tcc.bookinghotel.domain.exception.BaseException
import com.tcc.bookinghotel.domain.exception.CompanyRegistryException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus

abstract class TemplateHandlerSpecification {

    private val log = LoggerFactory.getLogger(javaClass)

    abstract suspend fun isTypeException(e: BaseException): Boolean

    abstract suspend fun applySpecification(e: BaseException): Pair<HttpErrorResponse, HttpStatus>

    suspend fun handlerTemplate(e: BaseException): Pair<HttpErrorResponse, HttpStatus> {
        return if (isTypeException(e)) {
            applySpecification(e)
        } else {
            log.error("Specification not identifiable exception ${e.type}")
            HttpErrorResponse("INTERNAL_SERVER_ERROR", "Error not identified") to HttpStatus.INTERNAL_SERVER_ERROR
        }
    }
}