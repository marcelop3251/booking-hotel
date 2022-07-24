package com.tcc.bookinghotel.domain.usecase

import com.tcc.bookinghotel.domain.entity.Booking
import com.tcc.bookinghotel.domain.entity.StatusBooking
import com.tcc.bookinghotel.domain.repository.BookingRepository
import com.tcc.bookinghotel.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow

class FindAllBooking(
    private val bookingRepository: BookingRepository,
    private val hotelRepository: HotelRepository,
) {

    suspend fun execute(customerId: Int): Flow<Booking> {
        return bookingRepository.findAllByStatusAndUserBackoffice(customerId, StatusBooking.CREATE)
    }
}