package com.tcc.bookinghotel.application.dto

import com.tcc.bookinghotel.domain.entity.Room
import java.math.BigDecimal

data class RegisterRoomResponse(
    val id: Int,
    val rentValue: BigDecimal,
    val quantity: Int,
    val description: String
) {
    constructor(room: Room): this (
        id = room.id!!,
        rentValue = room.rentValue,
        quantity = room.quantity,
        description = room.description
    )
}
