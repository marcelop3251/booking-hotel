package com.tcc.bookinghotel.domain.usecase

import com.tcc.bookinghotel.domain.entity.Room
import com.tcc.bookinghotel.domain.repository.HotelRepository
import com.tcc.bookinghotel.domain.repository.RoomRepository

class RegisterNewRoom(
    val roomRepository: RoomRepository,
    val hotelRepository: HotelRepository
) {

    suspend fun execute(room: Room, hotelId: Int): Room {
//        val hotel = hotelRepository.findById(hotelId)
//        if (hotel.numberBedrooms.)
        val room = roomRepository.create(room, hotelId)

        return room;
    }
}