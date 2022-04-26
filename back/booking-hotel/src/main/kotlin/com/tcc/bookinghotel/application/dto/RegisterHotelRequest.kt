package com.tcc.bookinghotel.application.dto

import com.tcc.bookinghotel.domain.entity.Hotel

class RegisterHotelRequest(
    val numberBedrooms: Int,
    val name: String,
    val cnpj: String,
    val city: String
) {
    suspend fun toDomain() = Hotel(
        name = name,
        cnpj = cnpj,
        city = city,
        numberBedrooms = numberBedrooms
    )
}
