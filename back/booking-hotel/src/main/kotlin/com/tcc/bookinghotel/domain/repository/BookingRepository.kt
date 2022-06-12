package com.tcc.bookinghotel.domain.repository

import com.tcc.bookinghotel.domain.entity.Booking
import com.tcc.bookinghotel.domain.entity.StatusBooking
import kotlinx.coroutines.flow.Flow

interface BookingRepository {

    suspend fun create(booking: Booking, customerId: Int): Booking
    suspend fun countByRoomIdAndStatusIn(id: Int, status: List<StatusBooking>): Int
    suspend fun findAll(customerId: String): Flow<Booking>
}