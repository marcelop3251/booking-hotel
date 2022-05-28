package com.tcc.bookinghotel.domain.entity

import java.math.BigDecimal

data class Room(
    val id: Int? = null,
    val type: String,
    val rentValue: BigDecimal,
    val quantity: Int,
    val description: String
)