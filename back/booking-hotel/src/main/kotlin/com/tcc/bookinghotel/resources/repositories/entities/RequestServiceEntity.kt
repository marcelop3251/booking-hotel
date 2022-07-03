package com.tcc.bookinghotel.resources.repositories.entities

import java.time.LocalDateTime
import org.springframework.data.relational.core.mapping.Table

@Table("request_service")
class RequestServiceEntity(
    var id: Int?,
    val creation: LocalDateTime,
    val bookingId: Int,
    val serviceId: Int
)