package com.tcc.bookinghotel.domain.repository

import com.tcc.bookinghotel.domain.entity.Hotel

interface HotelRepository {
    suspend fun findByCNPJ(cnpj: String): Hotel?
    suspend fun create(hotel: Hotel, companyId: Int): Hotel
    suspend fun findById(hotelId: Int): Hotel
}