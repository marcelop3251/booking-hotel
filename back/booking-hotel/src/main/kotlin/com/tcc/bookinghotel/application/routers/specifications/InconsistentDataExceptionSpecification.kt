package com.tcc.bookinghotel.application.routers.specifications

import com.tcc.bookinghotel.application.dto.HttpErrorResponse
import com.tcc.bookinghotel.application.routers.ExceptionHandlerSpecification
import com.tcc.bookinghotel.domain.exception.BaseException
import com.tcc.bookinghotel.domain.exception.InconsistentDataException
import com.tcc.bookinghotel.domain.exception.TypeException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class InconsistentDataExceptionSpecification : TemplateHandlerSpecification(), ExceptionHandlerSpecification {

    override suspend fun handler(e: BaseException): Pair<HttpErrorResponse, HttpStatus> = handlerTemplate(e)

    override fun type(): TypeException = TypeException.INCONSISTENCE

    override suspend fun isTypeException(e: BaseException): Boolean = e is InconsistentDataException

    override suspend fun applySpecification(e: BaseException): Pair<HttpErrorResponse, HttpStatus> =
        HttpErrorResponse(e.type.name, e.message) to HttpStatus.UNPROCESSABLE_ENTITY

}