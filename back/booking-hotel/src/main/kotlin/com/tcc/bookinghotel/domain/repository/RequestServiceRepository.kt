package com.tcc.bookinghotel.domain.repository

import com.tcc.bookinghotel.domain.entity.RequestService
import com.tcc.bookinghotel.domain.entity.ServiceType
import com.tcc.bookinghotel.domain.entity.StatusBooking
import kotlinx.coroutines.flow.Flow

interface RequestServiceRepository {

    suspend fun findAllRequestServiceByCustomerAndBookingAndStatus(
        customerId: Int,
        statusBooking: StatusBooking,
        serviceType: ServiceType,
    ): Flow<RequestService>

    suspend fun create(requestService: RequestService): RequestService
}