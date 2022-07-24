package com.tcc.bookinghotel.domain.usecase

import com.tcc.bookinghotel.domain.entity.Service
import com.tcc.bookinghotel.domain.repository.ServiceRepository

class CreateService(
    val serviceRepository: ServiceRepository
) {

    suspend fun execute(service: Service): Service {
        return serviceRepository.create(service)
    }
}