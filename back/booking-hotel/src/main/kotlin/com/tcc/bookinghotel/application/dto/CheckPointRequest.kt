package com.tcc.bookinghotel.application.dto

import com.tcc.bookinghotel.domain.entity.CheckPoint
import com.tcc.bookinghotel.domain.entity.StatusBooking
import java.time.LocalDateTime

class CheckPointRequest(
    val type: String,
    val hotelId: Int,
    val customerId: Int,
    val bookingId: Int
) {
    fun toDomain(): CheckPoint {
       return CheckPoint(
            type = StatusBooking.valueOf(type),
            createdAt = LocalDateTime.now(),
        )
    }
}