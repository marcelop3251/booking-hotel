package com.tcc.bookinghotel.resources.repositories.sql

import com.tcc.bookinghotel.domain.entity.Service
import com.tcc.bookinghotel.domain.entity.ServiceType
import com.tcc.bookinghotel.domain.repository.ServiceRepository
import com.tcc.bookinghotel.resources.repositories.entities.ServiceEntity
import com.tcc.bookinghotel.resources.repositories.sql.spring.ServiceRepositorySpring
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
class ServiceRepositoryImpl(
    val serviceRepositorySpring: ServiceRepositorySpring
) : ServiceRepository {

    override suspend fun findAllServices(serviceType: ServiceType): Flow<Service> {
        return serviceRepositorySpring.findAllByType(serviceType.name).map { it.toDomain() }
    }

    override suspend fun findById(id: Int): Service? {
        return serviceRepositorySpring.findById(id)?.toDomain()
    }

    override suspend fun create(service: Service): Service {
        return serviceRepositorySpring.save(ServiceEntity(service)).toDomain()
    }
}