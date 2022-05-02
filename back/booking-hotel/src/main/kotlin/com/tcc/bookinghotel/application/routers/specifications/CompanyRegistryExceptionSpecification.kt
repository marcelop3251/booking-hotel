package com.tcc.bookinghotel.application.routers.specifications

import com.tcc.bookinghotel.application.dto.HttpErrorResponse
import com.tcc.bookinghotel.application.routers.ExceptionHandlerSpecification
import com.tcc.bookinghotel.domain.exception.BaseException
import com.tcc.bookinghotel.domain.exception.CompanyRegistryException
import com.tcc.bookinghotel.domain.exception.TypeException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class CompanyRegistryExceptionSpecification : TemplateHandlerSpecification(), ExceptionHandlerSpecification {

    override suspend fun isTypeException(e: BaseException): Boolean = (e is CompanyRegistryException)

    override suspend fun applySpecification(e: BaseException): Pair<HttpErrorResponse, HttpStatus> =
        HttpErrorResponse(e.type.name, e.message) to HttpStatus.CONFLICT

    override suspend fun handler(e: BaseException): Pair<HttpErrorResponse, HttpStatus> {
       return handlerTemplate(e)
    }

    override fun type(): TypeException = TypeException.COMPANY_REGISTRATION
}