package com.tcc.bookinghotel.application.beans

import com.tcc.bookinghotel.application.routers.ExceptionHandlerSpecification
import com.tcc.bookinghotel.domain.exception.TypeException
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeansException {

    @Bean
    fun buildException(exceptionHandlerSpecification: List<ExceptionHandlerSpecification>): Map<TypeException, ExceptionHandlerSpecification> {
        return exceptionHandlerSpecification.associateBy { it.type() }
    }
}