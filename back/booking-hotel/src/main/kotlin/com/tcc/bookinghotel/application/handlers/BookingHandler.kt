package com.tcc.bookinghotel.application.handlers

import com.tcc.bookinghotel.domain.entity.StatusBooking
import com.tcc.bookinghotel.domain.usecase.UpdateStatusBooking
import com.tcc.bookinghotel.domain.usecase.admin.FindAllBookingAdminByStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class BookingHandler(
    val findAllBookingAdmin: FindAllBookingAdminByStatus,
    val updateStatusBooking: UpdateStatusBooking,
) {

    suspend fun getAllByStatus(request: ServerRequest): ServerResponse {
        val userBackoffice = request.headers().firstHeader("x-customer-id")!!.toInt()
        val status = request.pathVariable("status").let { StatusBooking.valueOf(it) }
        return ServerResponse.ok().bodyAndAwait(findAllBookingAdmin.execute(userBackoffice, status))
    }

    suspend fun doCheckin(request: ServerRequest): ServerResponse {
        val bookingId = request.pathVariable("id")
        updateStatusBooking.execute(bookingId.toInt(), StatusBooking.CHECK_IN_APPROVED)
        return ServerResponse.noContent().buildAndAwait()
    }

    suspend fun doCheckOut(request: ServerRequest): ServerResponse {
        val bookingId = request.pathVariable("id")
        updateStatusBooking.execute(bookingId.toInt(), StatusBooking.CHECK_OUT_APPROVED)
        return ServerResponse.noContent().buildAndAwait()
    }
}