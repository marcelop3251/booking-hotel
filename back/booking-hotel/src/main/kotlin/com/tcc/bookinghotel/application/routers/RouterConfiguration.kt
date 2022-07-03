package com.tcc.bookinghotel.application.routers

import com.tcc.bookinghotel.application.handlers.HotelHandler
import com.tcc.bookinghotel.application.handlers.RegisterCompanyHandler
import com.tcc.bookinghotel.application.handlers.RegisterCustomerHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
        onError<Exception> { exception, request -> exceptionHandler.handler(exception, request) }
    }

    @Bean
    fun HotelRoutes(
        hotelHandler: HotelHandler
    ) = coRouter {
        POST("/room/{hotel_id}", hotelHandler::registerRoom)
        GET("/room/{room_id}", hotelHandler::findByRoomId)

        "/hotel".nest {
            POST("/booking", hotelHandler::booking)
            GET("/booking", hotelHandler::findAllBooking)
            GET("/check-in", hotelHandler::findAllBookingApproved)
            POST("/{company_id}", hotelHandler::registerHotel)
            GET("", hotelHandler::findAll)
        }
    }
}

