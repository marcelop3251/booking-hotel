package com.tcc.bookinghotel.domain.repository

import com.tcc.bookinghotel.domain.entity.Hotel
import kotlinx.coroutines.flow.Flow

interface HotelRepository {
    suspend fun findByCNPJ(cnpj: String): Hotel?
    suspend fun create(hotel: Hotel, companyId: Int): Hotel
    suspend fun findById(hotelId: Int): Hotel?
    suspend fun findAll(): Flow<Hotel>
    suspend fun findByRoomId(roomId: Int): Hotel?
}