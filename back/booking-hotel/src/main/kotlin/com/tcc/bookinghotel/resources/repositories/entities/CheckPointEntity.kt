package com.tcc.bookinghotel.resources.repositories.entities

import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(value = "check_point")
class CheckPointEntity(
    @Id
    val id: Int?,
    val type: String,
    val createdAt: LocalDateTime,
    val hotelId: Int,
    val customerId: Int,
    val bookingId: Int
)