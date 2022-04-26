package com.tcc.bookinghotel.application.routers

import com.tcc.bookinghotel.application.handlers.HotelHandler
import com.tcc.bookinghotel.application.handlers.RegisterCompanyHandler
import com.tcc.bookinghotel.application.handlers.RegisterCustomerHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfiguration {

    @Bean
    fun hotelRoutes(hotelHandler: HotelHandler) = coRouter {
        GET("/hello", hotelHandler::registerHotel)
        onError<Exception>(ExceptionHandler::handler)
    }

    @Bean
    fun registerRoutes(
        registerCustomerHandler: RegisterCustomerHandler,
        registerCompanyHandler: RegisterCompanyHandler,
        hotelHandler: HotelHandler
    ) = coRouter {
        "/register".nest {
            POST("/customer", registerCustomerHandler::registerCustomer)
            POST("/company", registerCompanyHandler::register)
            POST("/hotel/{company_id}", hotelHandler::registerHotel)
            POST("room/{hotel_id}", hotelHandler::registerRoom)
        }
        onError<Exception>(ExceptionHandler::handler)
    }
}