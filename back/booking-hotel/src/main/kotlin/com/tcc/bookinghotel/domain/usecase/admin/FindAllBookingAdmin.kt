package com.tcc.bookinghotel.domain.usecase.admin

import com.tcc.bookinghotel.domain.entity.Booking
import com.tcc.bookinghotel.domain.entity.StatusBooking
import com.tcc.bookinghotel.domain.repository.BookingRepository
import kotlinx.coroutines.flow.Flow

class FindAllBookingAdminByStatus(
    val bookingRepository: BookingRepository
) {

    suspend fun execute(userBackoffice: Int, statusBooking: StatusBooking): Flow<Booking> {
        return bookingRepository.findAllByStatusAndUserBackoffice(userBackoffice, statusBooking)
    }
}