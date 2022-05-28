package com.tcc.bookinghotel.application.dto

import com.tcc.bookinghotel.domain.entity.Room
import java.math.BigDecimal

class RegisterRoomRequest(
    val rentValue: BigDecimal,
    val quantity: Int,
    val description: String,
    val type: String,
) {

    suspend fun toDomain() = Room(
        rentValue = rentValue,
        quantity = quantity,
        description = description,
        type = type,
    )
}