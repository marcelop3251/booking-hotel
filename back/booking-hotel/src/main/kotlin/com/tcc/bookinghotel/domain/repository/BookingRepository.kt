package com.tcc.bookinghotel.domain.repository

import com.tcc.bookinghotel.domain.entity.Booking
import com.tcc.bookinghotel.domain.entity.StatusBooking
import kotlinx.coroutines.flow.Flow

interface BookingRepository {

    suspend fun create(booking: Booking, customerId: Int, roomId: Int): Booking
    suspend fun countByRoomIdAndStatusIn(id: Int, status: List<StatusBooking>): Int
    suspend fun findAll(customerId: Int): Flow<Booking>
    suspend fun findByCustomerIdAndStatus(customerId: Int, statusBooking: StatusBooking): Flow<Booking>
    suspend fun findById(bookingId: Int): Booking?
    suspend fun findAllByStatusAndUserBackoffice(customerId: Int, statusBooking: StatusBooking): Flow<Booking>
    suspend fun updateStatusCheckingById(statusBooking: StatusBooking, id: Int)
}