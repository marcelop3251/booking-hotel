package com.tcc.bookinghotel.domain.usecase

import com.tcc.bookinghotel.domain.entity.RequestService
import com.tcc.bookinghotel.domain.entity.StatusBooking
import com.tcc.bookinghotel.domain.repository.RequestServiceRepository
import kotlinx.coroutines.flow.Flow

class FindAllRequestServices(
    val requestServiceRepository: RequestServiceRepository
) {

    suspend fun execute(customerId: Int): Flow<RequestService> {
        return requestServiceRepository.findAllRequestServiceByCustomerAndBookingAndStatus(customerId, StatusBooking.CHECK_IN_APPROVED)
    }
}