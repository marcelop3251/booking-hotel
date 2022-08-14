package com.tcc.bookinghotel.domain.usecase

import com.tcc.bookinghotel.domain.entity.Booking
import com.tcc.bookinghotel.domain.entity.StatusBooking
import com.tcc.bookinghotel.domain.repository.BookingRepository
import com.tcc.bookinghotel.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FindAllBooking(
    private val bookingRepository: BookingRepository,
    private val hotelRepository: HotelRepository,
) {

    suspend fun execute(customerId: Int): Flow<Booking> {
        return bookingRepository.findByCustomerId(customerId)
    }

    suspend fun execute(customerId: Int, statusBooking: StatusBooking): Flow<Booking> {
        return bookingRepository.findByCustomerIdAndStatus(customerId, statusBooking)
    }
}