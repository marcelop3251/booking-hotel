package com.tcc.bookinghotel.domain.entity

import java.time.LocalDate

data class RequestService(
    val id: Int? = null,
    val createdAt: LocalDate,
    val booking: Booking,
    val service: Service
)