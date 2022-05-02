package com.tcc.bookinghotel.domain.entity

import kotlinx.coroutines.flow.Flow


data class Hotel(
    val id: Int? = null,
    val name: String,
    val cnpj: String,
    val city: String,
    val numberBedrooms: Int,
    val room: List<Room>? = null,
)