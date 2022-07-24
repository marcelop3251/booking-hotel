package com.tcc.bookinghotel.application.handlers

import com.tcc.bookinghotel.application.dto.BookingRequest
import com.tcc.bookinghotel.application.dto.RegisterHotelRequest
import com.tcc.bookinghotel.application.dto.RegisterHotelResponse
import com.tcc.bookinghotel.application.dto.RegisterRoomRequest
import com.tcc.bookinghotel.application.dto.RegisterRoomResponse
import com.tcc.bookinghotel.application.dto.RequestServiceRequest
import com.tcc.bookinghotel.domain.entity.ServiceType
import com.tcc.bookinghotel.domain.entity.StatusBooking
import com.tcc.bookinghotel.domain.exception.NotFoundItemException
import com.tcc.bookinghotel.domain.exception.TypeException
import com.tcc.bookinghotel.domain.usecase.BookingHotel
import com.tcc.bookinghotel.domain.usecase.CreateRequestService
import com.tcc.bookinghotel.domain.usecase.FindAllBooking
import com.tcc.bookinghotel.domain.usecase.FindAllHotel
import com.tcc.bookinghotel.domain.usecase.FindAllRequestServices
import com.tcc.bookinghotel.domain.usecase.FindBookingPendingCheckIn
import com.tcc.bookinghotel.domain.usecase.FindBookingPendingCheckOut
import com.tcc.bookinghotel.domain.usecase.FindHoteByRoomId
import com.tcc.bookinghotel.domain.usecase.FindAllServices
import com.tcc.bookinghotel.domain.usecase.RegisterNewHotel
import com.tcc.bookinghotel.domain.usecase.RegisterNewRoom
import com.tcc.bookinghotel.domain.usecase.UpdateStatusBooking
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait

private const val COMPANY_ID_PATH_VARIABLE = "company_id"

@Component
class HotelHandler(
    val registerNewHotel: RegisterNewHotel,
    val registerRoom: RegisterNewRoom,
    val findAlHotel: FindAllHotel,
    val findHotelByRoomId: FindHoteByRoomId,
    val bookingHotel: BookingHotel,
    val findAllBooking: FindAllBooking,
    val findBookingPendingCheckIn: FindBookingPendingCheckIn,
    val findBookingPendingCheckOut: FindBookingPendingCheckOut,
    val findAllServices: FindAllServices,
    val findAllRequestServices: FindAllRequestServices,
    val createRequestService: CreateRequestService,
    val updateStatusBooking: UpdateStatusBooking,
) {

    val log = LoggerFactory.getLogger(javaClass)

    suspend fun registerHotel(request: ServerRequest): ServerResponse {
        log.info("Start request to register hotel")
        val companyId = request.pathVariable(COMPANY_ID_PATH_VARIABLE)
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

    suspend fun findAll(request: ServerRequest): ServerResponse {
        log.info("Start find all hotel")
        //val companyId = request.pathVariable(COMPANY_ID_PATH_VARIABLE)
        return ServerResponse.ok().bodyAndAwait(findAlHotel.execute()).also {
            log.info("Finish find all hotel")
        }
    }

    suspend fun findByRoomId(request: ServerRequest): ServerResponse {
        log.info("start find hotel by room id");
        val roomId = request.pathVariable("room_id")
        val hotel = findHotelByRoomId.execute(roomId.toInt()) ?: throw NotFoundItemException(TypeException.ROOM_NOT_FOUND, "Not was possible identifier romm by id ${roomId}");
        return ServerResponse.ok().bodyValueAndAwait(hotel)
    }

    suspend fun booking(request: ServerRequest): ServerResponse {
        log.info("Start making booking hotel")
        val bookingRequest = request.awaitBody<BookingRequest>()
        val customerId = request.headers().firstHeader("x-customer-id")!!
        val booking = bookingHotel.execute(bookingRequest.toDomain(), customerId.toInt(), bookingRequest.roomId)
        return ServerResponse.ok().bodyValueAndAwait(booking)
    }

    suspend fun findAllBooking(request: ServerRequest): ServerResponse {
        val customerId = request.headers().firstHeader("x-customer-id")!!.toInt()
        return ServerResponse.ok().bodyAndAwait(findAllBooking.execute(customerId))
    }

    suspend fun findAllBookingApproved(request: ServerRequest): ServerResponse {
        val customerId = request.headers().firstHeader("x-customer-id")!!.toInt()
        return ServerResponse.ok().bodyAndAwait(findBookingPendingCheckIn.execute(customerId))
    }

    suspend fun findAllBookingFinalized(request: ServerRequest): ServerResponse {
        val customerId = request.headers().firstHeader("x-customer-id")!!.toInt()
        return ServerResponse.ok().bodyAndAwait(findBookingPendingCheckOut.execute(customerId))
    }

    suspend fun findAllServices(request: ServerRequest): ServerResponse {
        val pathVariable = request.pathVariable("type")
        return ServerResponse.ok().bodyAndAwait(findAllServices.execute(ServiceType.valueOf(pathVariable)))
    }

    suspend fun findAllRequestServices(request: ServerRequest): ServerResponse {
        val customerId = request.headers().firstHeader("x-customer-id")!!
        return ServerResponse.ok()
            .bodyAndAwait(findAllRequestServices.execute(customerId.toInt()))
    }

    suspend fun createRequestService(request: ServerRequest): ServerResponse {
        val requestServiceDto = request.awaitBody<RequestServiceRequest>()
        return ServerResponse.ok().bodyValueAndAwait(createRequestService.execute(requestServiceDto.serviceId, requestServiceDto.bookingId))
    }

    suspend fun doCheckin(request: ServerRequest): ServerResponse {
        val bookingId = request.pathVariable("id")
        updateStatusBooking.execute(bookingId.toInt(), StatusBooking.CHECK_IN)
        return ServerResponse.noContent().buildAndAwait()
    }

    suspend fun doCheckOut(request: ServerRequest): ServerResponse {
        val bookingId = request.pathVariable("id")
        updateStatusBooking.execute(bookingId.toInt(), StatusBooking.CHECK_OUT)
        return ServerResponse.noContent().buildAndAwait()
    }
}