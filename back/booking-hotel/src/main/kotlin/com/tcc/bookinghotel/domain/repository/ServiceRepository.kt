package com.tcc.bookinghotel.domain.repository

import com.tcc.bookinghotel.domain.entity.Service
import com.tcc.bookinghotel.domain.entity.ServiceType
import kotlinx.coroutines.flow.Flow

interface ServiceRepository {

    suspend fun findAllServices(serviceType: ServiceType): Flow<Service>
    suspend fun findById(id: Int): Service?
    suspend fun create(service: Service): Service
}