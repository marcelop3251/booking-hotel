package com.tcc.bookinghotel.domain.usecase

import com.tcc.bookinghotel.domain.entity.RequestService
import com.tcc.bookinghotel.domain.exception.NotFoundItemException
import com.tcc.bookinghotel.domain.exception.TypeException
import com.tcc.bookinghotel.domain.repository.BookingRepository
import com.tcc.bookinghotel.domain.repository.RequestServiceRepository
import com.tcc.bookinghotel.domain.repository.ServiceRepository
import java.time.LocalDateTime

class CreateRequestService(
    val requestServiceRepository: RequestServiceRepository,
    val bookingRepository: BookingRepository,
    val serviceRepository: ServiceRepository,
) {

    suspend fun execute(serviceId: Int, bookingId: Int): RequestService {
        val booking = bookingRepository.findById(bookingId) ?: throw NotFoundItemException(TypeException.BOOKING_NOT_FOUND, "Not was found booking to bookingId ${bookingId}")
        val service = serviceRepository.findById(serviceId) ?: throw NotFoundItemException(TypeException.SERVICE_NOT_FOUND, "Not was found service to bookingId ${serviceId}")
        return requestServiceRepository.create(RequestService(
            createdAt = LocalDateTime.now(),
            booking = booking,
            service = service
        ))
    }
}