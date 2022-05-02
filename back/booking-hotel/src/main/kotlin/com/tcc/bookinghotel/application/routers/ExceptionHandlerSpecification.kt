package com.tcc.bookinghotel.application.routers

import com.tcc.bookinghotel.application.dto.HttpErrorResponse
import com.tcc.bookinghotel.domain.exception.BaseException
import com.tcc.bookinghotel.domain.exception.TypeException
import org.springframework.http.HttpStatus

interface ExceptionHandlerSpecification {

    suspend fun handler(e: BaseException): Pair<HttpErrorResponse, HttpStatus>

    fun type(): TypeException
}