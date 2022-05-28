package com.tcc.bookinghotel.domain.usecase

import com.tcc.bookinghotel.domain.entity.Hotel
import com.tcc.bookinghotel.domain.repository.HotelRepository
import com.tcc.bookinghotel.domain.repository.RoomRepository

class FindHoteByRoomId(
    val hotelRepository: HotelRepository,
) {

    suspend fun execute(roomId: Int): Hotel? {
        return hotelRepository.findByRoomId(roomId);
    }
}
