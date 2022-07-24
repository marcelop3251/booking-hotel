package com.tcc.bookinghotel.resources.repositories.entities

import com.tcc.bookinghotel.domain.entity.Booking
import com.tcc.bookinghotel.domain.entity.RequestService
import com.tcc.bookinghotel.domain.entity.Service
import java.time.LocalDateTime
import org.springframework.data.relational.core.mapping.Table

@Table("request_service")
class RequestServiceEntity(
    var id: Int? = null,
    val createdAt: LocalDateTime,
    val bookingId: Int,
    val serviceId: Int
) {
    constructor(requestService: RequestService) : this(
        createdAt = requestService.createdAt,
        bookingId = requestService.booking.id!!,
        serviceId = requestService.service.id!!
    )

    fun toDomain(booking: Booking, service: Service) = RequestService(
        id = id!!,
        createdAt = createdAt,
        booking = booking,
        service = service
    )
}