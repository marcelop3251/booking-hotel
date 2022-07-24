package com.tcc.bookinghotel.resources.repositories.entities

import com.tcc.bookinghotel.domain.entity.Service
import com.tcc.bookinghotel.domain.entity.ServiceType
import java.math.BigDecimal
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("service")
class ServiceEntity(
    @Id
    var id: Int? = null,
    val name: String,
    val price: BigDecimal,
    val description: String,
    val type: String
) {
    constructor(service: Service) : this(
        name = service.name,
        price = service.price,
        description = service.description,
        type = service.type.name
    )

    fun toDomain(): Service = Service(
        id = id!!,
        name = name,
        price = price,
        description = description,
        type = ServiceType.valueOf(type)
    )
}