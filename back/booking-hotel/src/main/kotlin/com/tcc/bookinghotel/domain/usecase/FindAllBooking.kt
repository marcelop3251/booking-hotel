package com.tcc.bookinghotel.domain.usecase

import com.tcc.bookinghotel.domain.entity.Booking
import com.tcc.bookinghotel.domain.repository.BookingRepository
import kotlinx.coroutines.flow.Flow

class FindAllBooking(
    private val bookingRepository: BookingRepository,
) {

    suspend fun execute(customerId: String): Flow<Booking> {
        return bookingRepository.findAll(customerId)
    }
}