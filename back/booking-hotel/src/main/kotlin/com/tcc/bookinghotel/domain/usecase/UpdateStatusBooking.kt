package com.tcc.bookinghotel.domain.usecase

import com.tcc.bookinghotel.domain.entity.StatusBooking
import com.tcc.bookinghotel.domain.repository.BookingRepository
import org.slf4j.LoggerFactory

class UpdateStatusBooking(
    val bookingRepository: BookingRepository
) {
    val log = LoggerFactory.getLogger(javaClass)

    suspend fun execute(bookingId: Int, statusBooking: StatusBooking) {
        val booking = bookingRepository.findById(bookingId)
        when (statusBooking) {
            StatusBooking.CHECK_IN -> {
                if (booking?.status == StatusBooking.CREATE) {
                    bookingRepository.updateStatusCheckingById(statusBooking, bookingId)
                }
            }
            StatusBooking.CHECK_IN_APPROVED -> {
                if (booking?.status == StatusBooking.CHECK_IN) {
                    bookingRepository.updateStatusCheckingById(statusBooking, bookingId)
                }
            }
            StatusBooking.CHECK_OUT_APPROVED -> {
                if (booking?.status == StatusBooking.CHECK_OUT) {
                    bookingRepository.updateStatusCheckingById(statusBooking, bookingId)
                }
            }
            else -> {
                log.info("Not was possible aplicar update status booking")
            }
        }
    }
}