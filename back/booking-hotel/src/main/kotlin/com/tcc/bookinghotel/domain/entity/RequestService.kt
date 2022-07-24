package com.tcc.bookinghotel.domain.entity

import java.time.LocalDateTime

data class RequestService(
    val id: Int? = null,
    val createdAt: LocalDateTime,
    val booking: Booking,
    val service: Service
)