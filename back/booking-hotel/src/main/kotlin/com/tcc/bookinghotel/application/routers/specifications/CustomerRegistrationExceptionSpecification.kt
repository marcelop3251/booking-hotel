package com.tcc.bookinghotel.application.routers.specifications

import com.tcc.bookinghotel.application.dto.HttpErrorResponse
import com.tcc.bookinghotel.application.routers.ExceptionHandlerSpecification
import com.tcc.bookinghotel.domain.exception.BaseException
import com.tcc.bookinghotel.domain.exception.CustomerRegistryException
import com.tcc.bookinghotel.domain.exception.TypeException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class CustomerRegistrationExceptionSpecification : TemplateHandlerSpecification(), ExceptionHandlerSpecification  {

    override suspend fun handler(e: BaseException): Pair<HttpErrorResponse, HttpStatus> {
        return handlerTemplate(e)
    }

    override fun type(): TypeException  = TypeException.CUSTOMER_REGISTRATION

    override suspend fun isTypeException(e: BaseException): Boolean = e is CustomerRegistryException

    override suspend fun applySpecification(e: BaseException): Pair<HttpErrorResponse, HttpStatus> {
        return HttpErrorResponse(e.type.name, e.message) to HttpStatus.CONFLICT
    }
}