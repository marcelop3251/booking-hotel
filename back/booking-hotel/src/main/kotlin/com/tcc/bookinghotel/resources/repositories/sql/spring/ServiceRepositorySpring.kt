package com.tcc.bookinghotel.resources.repositories.sql.spring

import com.tcc.bookinghotel.resources.repositories.entities.ServiceEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ServiceRepositorySpring : CoroutineCrudRepository<ServiceEntity, Int> {

    suspend fun findAllByType(serviceType: String): Flow<ServiceEntity>
    suspend fun findByIdAndType(serviceId: Int, type: String): ServiceEntity?
}