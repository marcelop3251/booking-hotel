package com.tcc.bookinghotel.domain.usecase

import com.tcc.bookinghotel.domain.entity.Booking
import com.tcc.bookinghotel.domain.entity.StatusBooking
import com.tcc.bookinghotel.domain.repository.BookingRepository
import com.tcc.bookinghotel.domain.repository.CheckPointRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

class FindBookingPendingCheckIn(
    val bookingRepository: BookingRepository,
    val checkPointRepository: CheckPointRepository,
) {

    suspend fun execute(customerId: String): Flow<Booking> {
        return bookingRepository.findByCustomerIdAndStatus(customerId, StatusBooking.APPROVED)
            .filter {
                checkPointRepository.notExistCheckPoint(customerId, it.id, it.hotel?.id)
            }
    }
}