package com.tcc.bookinghotel.application.handlers

import com.tcc.bookinghotel.application.dto.RegisterHotelRequest
import com.tcc.bookinghotel.application.dto.RegisterHotelResponse
import com.tcc.bookinghotel.application.dto.RegisterRoomRequest
import com.tcc.bookinghotel.application.dto.RegisterRoomResponse
import com.tcc.bookinghotel.domain.usecase.RegisterNewHotel
import com.tcc.bookinghotel.domain.usecase.RegisterNewRoom
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class HotelHandler(
    val registerNewHotel: RegisterNewHotel,
    val registerRoom: RegisterNewRoom
) {

    val log = LoggerFactory.getLogger(javaClass)

    suspend fun registerHotel(request: ServerRequest): ServerResponse {
        log.info("Start request to register hotel")
        val companyId = request.pathVariable("company_id")
        val hotel = registerNewHotel.execute(request.awaitBody<RegisterHotelRequest>().toDomain(), companyId.toInt())
        return ServerResponse.ok().bodyValueAndAwait(RegisterHotelResponse(hotel)).also {
            log.info("Finished request to register hotel with success")
        }
    }

    suspend fun registerRoom(request: ServerRequest): ServerResponse {
        log.info("Start request to register room")
        val hotelId = request.pathVariable("hotel_id")
        val room = registerRoom.execute(request.awaitBody<RegisterRoomRequest>().toDomain(), hotelId.toInt())
        return ServerResponse.ok().bodyValueAndAwait(RegisterRoomResponse(room))
    }
}