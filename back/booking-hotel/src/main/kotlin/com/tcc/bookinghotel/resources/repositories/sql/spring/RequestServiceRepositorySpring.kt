package com.tcc.bookinghotel.resources.repositories.sql.spring

import com.tcc.bookinghotel.resources.repositories.entities.RequestServiceEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface RequestServiceRepositorySpring : CoroutineCrudRepository<RequestServiceEntity, Int> {

    suspend fun findByBookingId(id: Int): Flow<RequestServiceEntity>
}