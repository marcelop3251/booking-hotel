package com.tcc.bookinghotel.resources.repositories.sql.spring

import com.tcc.bookinghotel.resources.repositories.entities.CheckPointEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CheckPointRepositorySpring : CoroutineCrudRepository<CheckPointEntity, Int> {

    suspend fun existsByCustomerIdAndBookingIdAndHotelId(customerId: Int, bookingId: Int?, hotelId: Int?): Boolean
}