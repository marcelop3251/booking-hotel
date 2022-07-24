package com.tcc.bookinghotel.domain.usecase

import com.tcc.bookinghotel.domain.entity.Service
import com.tcc.bookinghotel.domain.entity.ServiceType
import com.tcc.bookinghotel.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.Flow

class FindAllServices(
    val serviceRepository: ServiceRepository,
) {

    suspend fun execute(serviceType: ServiceType): Flow<Service> {
        return serviceRepository.findAllServices(serviceType)
    }
}