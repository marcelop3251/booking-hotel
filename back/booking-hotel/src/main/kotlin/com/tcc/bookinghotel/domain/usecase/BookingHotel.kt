package com.tcc.bookinghotel.domain.usecase

import com.tcc.bookinghotel.domain.entity.Booking
import com.tcc.bookinghotel.domain.entity.Room
import com.tcc.bookinghotel.domain.entity.StatusBooking
import com.tcc.bookinghotel.domain.exception.NotFoundItemException
import com.tcc.bookinghotel.domain.exception.RoomUnavailableException
import com.tcc.bookinghotel.domain.exception.TypeException
import com.tcc.bookinghotel.domain.repository.BookingRepository
import com.tcc.bookinghotel.domain.repository.RoomRepository

class BookingHotel(
    private val bookingRepository: BookingRepository,
    private val roomRepository: RoomRepository,
) {

    suspend fun execute(booking: Booking, customerId: Int, roomId: Int): Booking {
        val room = roomRepository.findById(roomId) ?: throw NotFoundItemException(TypeException.ROOM_NOT_FOUND, "Room not found for id: ${roomId}")
        val bookingFromDB = bookingRepository.countByRoomIdAndStatusIn(roomId, listOf(StatusBooking.CREATE, StatusBooking.CHECK_IN))
        roomIsAvailable(room, bookingFromDB)
        return bookingRepository.create(booking, customerId, roomId)
    }

    private fun roomIsAvailable(room: Room, bookingFromDb: Int) {
        if (room.quantity <= bookingFromDb) throw RoomUnavailableException(TypeException.ROOM_UNAVAILABLE, "Room not unavailable")
    }
}