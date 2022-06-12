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

    suspend fun execute(booking: Booking, customerId: Int): Booking {
        val room = roomRepository.findById(booking.room?.id!!) ?: throw NotFoundItemException(TypeException.ROOM_NOT_FOUND, "Room not found for id: ${booking.room.id}")
        val bookingFromDB = bookingRepository.countByRoomIdAndStatusIn(booking.room.id, listOf(StatusBooking.CREATE, StatusBooking.APPROVED))
        roomIsAvailable(room, bookingFromDB)
        return bookingRepository.create(booking, customerId)
    }

    private fun roomIsAvailable(room: Room, bookingFromDb: Int) {
        if (room.quantity <= bookingFromDb) throw RoomUnavailableException(TypeException.ROOM_UNAVAILABLE, "Room not unavailable")
    }
}