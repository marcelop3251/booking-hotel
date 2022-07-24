package com.tcc.bookinghotel.domain.entity

import java.math.BigDecimal

data class Service(
    val id: Int? = null,
    val name: String,
    val price: BigDecimal,
    val description: String,
    val type: ServiceType
)