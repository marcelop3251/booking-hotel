package com.tcc.bookinghotel.domain.usecase

import com.tcc.bookinghotel.domain.entity.Room
import com.tcc.bookinghotel.domain.exception.NotFoundItemException
import com.tcc.bookinghotel.domain.exception.RoomUnavailableException
import com.tcc.bookinghotel.domain.exception.TypeException
import com.tcc.bookinghotel.domain.repository.HotelRepository
import com.tcc.bookinghotel.domain.repository.RoomRepository

class RegisterNewRoom(
    val roomRepository: RoomRepository,
    val hotelRepository: HotelRepository
) {

    suspend fun execute(room: Room, hotelId: Int): Room {
        val hotel = hotelRepository.findById(hotelId) ?: throw NotFoundItemException(
            TypeException.HOTEL_NOT_FOUND,
            "Hotel not found exception ${room.id}"
        )
        val quantityBedrooms = roomRepository.getTotalBedrooms(hotelId)
        if (hotel.numberBedrooms <= quantityBedrooms.plus(room.quantity)) {
            throw RoomUnavailableException(TypeException.ROOM_UNAVAILABLE, "Room unavailable exception")
        }

        return roomRepository.create(room, hotelId);
    }
}