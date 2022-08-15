package com.tcc.bookinghotel.application.routers

import com.tcc.bookinghotel.application.handlers.BookingHandler
import com.tcc.bookinghotel.application.handlers.HotelHandler
import com.tcc.bookinghotel.application.handlers.RegisterCompanyHandler
import com.tcc.bookinghotel.application.handlers.RegisterCustomerHandler
import com.tcc.bookinghotel.application.handlers.ServiceHandler
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
        "/hotel".nest {
            GET("/room/{room_id}", hotelHandler::findByRoomId)
            POST("/booking", hotelHandler::booking)
            GET("/booking", hotelHandler::findAllBooking)
            GET("/booking/{status}", hotelHandler::findAllBookingStatus)
            GET("/check-in", hotelHandler::findAllBookingApproved)
            POST("/check-in/{id}", hotelHandler::doCheckin)
            GET("/check-out", hotelHandler::findAllBookingFinalized)
            POST("/check-out/{id}", hotelHandler::doCheckOut)
            GET("/services/{type}", hotelHandler::findAllServices)
            GET("/request/{type}", hotelHandler::findAllRequestByType)
            POST("/request", hotelHandler::createRequestService)
            GET("", hotelHandler::findAll)
            onError<Exception> { exception, request -> exceptionHandler.handler(exception, request) }
        }
    }

    @Bean
    fun servicesHoutes(
        serviceHandler: ServiceHandler,
        bookingHandler: BookingHandler,
        hotelHandler: HotelHandler
    ) = coRouter {
        "/admin".nest {
            POST("/service", serviceHandler::createService)
            GET("/booking/{status}", bookingHandler::getAllByStatus)
            POST("/booking/check-in/{id}", bookingHandler::doCheckin)
            POST("/booking/check-out/{id}", bookingHandler::doCheckOut)
            GET("/hotel", hotelHandler::findUserBackoffice)
            POST("/hotel", hotelHandler::registerHotel)
            POST("/hotel/room/{hotel_id}", hotelHandler::registerRoom)
            onError<Exception> { exception, request -> exceptionHandler.handler(exception, request) }
        }
    }
}

