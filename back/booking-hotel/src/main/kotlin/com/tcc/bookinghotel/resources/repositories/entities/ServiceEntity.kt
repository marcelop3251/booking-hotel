package com.tcc.bookinghotel.resources.repositories.entities

import java.math.BigDecimal
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("service")
class ServiceEntity(
    @Id
    var id: Int?,
    val name: String,
    val price: BigDecimal,
    val description: String,
    val type: String
)