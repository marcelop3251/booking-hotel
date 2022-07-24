package com.tcc.bookinghotel.resources.repositories.sql

import com.tcc.bookinghotel.domain.entity.RequestService
import com.tcc.bookinghotel.domain.entity.StatusBooking
import com.tcc.bookinghotel.domain.repository.RequestServiceRepository
import com.tcc.bookinghotel.resources.repositories.entities.RequestServiceEntity
import com.tcc.bookinghotel.resources.repositories.sql.spring.BookingRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.RequestServiceRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.ServiceRepositorySpring
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Component

@Component
class RequestServiceRepositoryImpl(
    val requestServiceRepositorySpring: RequestServiceRepositorySpring,
    val bookingRepositorySpring: BookingRepositorySpring,
    val servicesRepositorySpring: ServiceRepositorySpring
) : RequestServiceRepository {

    override suspend fun findAllRequestServiceByCustomerAndBookingAndStatus(
        customerId: Int,
        statusBooking: StatusBooking
    ): Flow<RequestService> {
       return bookingRepositorySpring.findByCustomerIdAndStatus(customerId, statusBooking.name)
            .map { bookingEntity -> requestServiceRepositorySpring.findByBookingId(bookingEntity.id!!)
                .map { it.toDomain(bookingEntity.toDomain(), servicesRepositorySpring.findById(it.serviceId)!!.toDomain()) }
            }.flattenConcat()
    }

    override suspend fun create(requestService: RequestService): RequestService {
        return requestServiceRepositorySpring.save(RequestServiceEntity(requestService)).let {
            requestService.copy(id = it.id)
        }
    }
}