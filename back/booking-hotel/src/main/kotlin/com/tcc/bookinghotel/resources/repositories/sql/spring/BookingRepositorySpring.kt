package com.tcc.bookinghotel.resources.repositories.sql.spring

import com.tcc.bookinghotel.resources.repositories.entities.BookingEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookingRepositorySpring : CoroutineCrudRepository<BookingEntity, Int> {

    suspend fun countByRoomIdAndStatusIn(id: Int, status: List<String>): Int
    suspend fun findAllByCustomerId(customerId: String): Flow<BookingEntity>
}