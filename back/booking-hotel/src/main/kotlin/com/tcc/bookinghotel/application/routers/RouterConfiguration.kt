package com.tcc.bookinghotel.application.routers

import com.tcc.bookinghotel.application.handlers.HotelHandler
import com.tcc.bookinghotel.application.handlers.RegisterCompanyHandler
import com.tcc.bookinghotel.application.handlers.RegisterCustomerHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.CoRouterFunctionDsl
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfiguration(
    private val exceptionHandler: ExceptionHandler
) {

    @Bean
    fun routes(
        registerCustomerHandler: RegisterCustomerHandler,
        registerCompanyHandler: RegisterCompanyHandler,
        hotelHandler: HotelHandler
    ) = coRouter {
        POST("/customer", registerCustomerHandler::registerCustomer)
        POST("/company", registerCompanyHandler::register)
        "/hotel".nest {
            POST("/{company_id}", hotelHandler::registerHotel)
            GET("", hotelHandler::findAll)
        }

        POST("/room/{hotel_id}", hotelHandler::registerRoom)
        onError<Exception> { exception, request -> exceptionHandler.handler(exception, request) }
    }

    fun searchHotelRoutes(
        hotelHandler: HotelHandler
    ) = coRouter {
        ""
    }
}

