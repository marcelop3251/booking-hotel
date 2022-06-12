package com.tcc.bookinghotel.application.dto

import com.tcc.bookinghotel.domain.entity.Booking
import com.tcc.bookinghotel.domain.entity.Room
import com.tcc.bookinghotel.domain.entity.StatusBooking
import java.time.LocalDate

class BookingRequest(
    val startDate: LocalDate,
    val endDate: LocalDate,
    val description: String = "",
    val status: StatusBooking = StatusBooking.CREATE,
    val room: Room?= null
) {
    fun toDomain() = Booking(
        startDate = startDate,
        endDate = endDate,
        description = description,
        status = status,
        room = room
    )
}