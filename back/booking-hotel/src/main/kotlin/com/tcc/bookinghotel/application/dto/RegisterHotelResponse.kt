package com.tcc.bookinghotel.application.dto

import com.tcc.bookinghotel.domain.entity.Hotel

data class RegisterHotelResponse(
    val id: Int,
    val numberBedrooms: Int,
    val name: String,
    val cnpj: String,
    val city: String
) {
    constructor(hotel: Hotel): this(
        id = hotel.id!!,
        numberBedrooms = hotel.numberBedrooms,
        name = hotel.name,
        cnpj = hotel.cnpj,
        city = hotel.city
    )
}