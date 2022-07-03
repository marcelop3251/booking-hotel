package com.tcc.bookinghotel.resources.repositories.sql

import com.tcc.bookinghotel.domain.repository.CheckPointRepository
import com.tcc.bookinghotel.resources.repositories.sql.spring.CheckPointRepositorySpring
import org.springframework.stereotype.Component

@Component
class CheckPointRepositoryImpl(
    private val checkPointRepositorySpring: CheckPointRepositorySpring
) : CheckPointRepository {

    override suspend fun notExistCheckPoint(customerId: String, bookingId: Int?, hotelId: Int?): Boolean {
        return !checkPointRepositorySpring.existsByCustomerIdAndBookingIdAndHotelId(customerId, bookingId, hotelId)
    }
}