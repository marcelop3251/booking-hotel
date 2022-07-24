package com.tcc.bookinghotel.domain.entity


import java.time.LocalDateTime

data class CheckPoint(
    val id: Int? = null,
    val type: StatusBooking,
    val createdAt: LocalDateTime
)