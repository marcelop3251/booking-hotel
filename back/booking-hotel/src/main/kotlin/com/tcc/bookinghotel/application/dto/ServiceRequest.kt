package com.tcc.bookinghotel.application.dto

import com.tcc.bookinghotel.domain.entity.Service
import com.tcc.bookinghotel.domain.entity.ServiceType
import java.math.BigDecimal

class ServiceRequest(
    val name: String,
    val price: BigDecimal,
    val description: String,
    val type: ServiceType
) {
    fun toDomain(): Service = Service(
        name = name,
        price = price,
        description = description,
        type = type
    )
}