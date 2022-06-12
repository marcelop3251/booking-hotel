package com.tcc.bookinghotel.domain.repository

import com.tcc.bookinghotel.domain.entity.Room

interface RoomRepository {
    suspend fun create(room: Room, hotelId: Int): Room
    suspend fun findById(id: Int): Room?
    suspend fun getTotalBedrooms(hotelId: Int): Int
}