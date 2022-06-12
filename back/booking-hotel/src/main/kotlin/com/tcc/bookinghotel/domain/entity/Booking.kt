package com.tcc.bookinghotel.domain.entity

import java.time.LocalDate

data class Booking(
    val id: Int ?= null,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val description: String,
    val status: StatusBooking,
    val room: Room ?= null
)