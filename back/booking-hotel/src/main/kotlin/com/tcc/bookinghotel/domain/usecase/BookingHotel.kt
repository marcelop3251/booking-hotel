package com.tcc.bookinghotel.domain.usecase

import com.tcc.bookinghotel.domain.entity.Booking
import com.tcc.bookinghotel.domain.entity.Room
import com.tcc.bookinghotel.domain.entity.StatusBooking
import com.tcc.bookinghotel.domain.exception.NotFoundItemException
import com.tcc.bookinghotel.domain.exception.RoomUnavailableException
import com.tcc.bookinghotel.domain.exception.TypeException
import com.tcc.bookinghotel.domain.repository.BookingRepository
import com.tcc.bookinghotel.domain.repository.RoomRepository
import kotlinx.coroutines.flow.filter

class BookingHotel(
    private val bookingRepository: BookingRepository,
    private val roomRepository: RoomRepository,
) {

    /*
    periodo alocado
    digamos que temos 2 quartos
    1 alugado para o periodo 13-08-2022 15-08-2022
    2 alugado para o periodo 13-08-2022 18-08-2022

    3 gostaria de alugar para o periodo de 14-08-2022 17-08-2022

    Não deve ter disponibilidade

    trazer todos as reservas que terminam depois da de inicio da hospedagem
     14-08-2022 > 15-08-2022

     iterar para saber se a data o periodo selecionado está ocupado
     b.startDate >= r.startDate || b.startDate <= r.endDate and b.endDate > r.startDate and b.endDate < r.startDate

    if (data

     */
    suspend fun execute(booking: Booking, customerId: Int, roomId: Int): Booking {
        val room = roomRepository.findById(roomId) ?: throw NotFoundItemException(TypeException.ROOM_NOT_FOUND, "Room not found for id: ${roomId}")
        val bookingFromDB = bookingRepository.countByRoomIdAndStatusInAndDate(
            roomId,
            listOf(StatusBooking.CREATE, StatusBooking.CHECK_IN, StatusBooking.CHECK_IN_APPROVED),
            booking.startDate,
            booking.endDate)

        roomIsAvailable(room, bookingFromDB)
        return bookingRepository.create(booking, customerId, roomId)
    }

    private fun roomIsAvailable(room: Room, bookingFromDb: Int) {
        if (room.quantity <= bookingFromDb) throw RoomUnavailableException(TypeException.ROOM_UNAVAILABLE, "Room not unavailable")
    }
}