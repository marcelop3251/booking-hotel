package com.tcc.bookinghotel.resources.repositories.entities

import com.tcc.bookinghotel.domain.entity.Company
import com.tcc.bookinghotel.domain.entity.Hotel
import io.azam.ulidj.ULID
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table

@Table("organization")
data class OrganizationEntity(
    @Id
    var id: Int?,
    val name: String,
    val cnpj: String,
    val city: String,
) {

    constructor(company: Company): this(
        id = company.id,
        name = company.name,
        cnpj = company.cnpj,
        city = company.city
    )

    constructor(hotel: Hotel) : this(
        id = hotel.id,
        name = hotel.name,
        cnpj = hotel.cnpj,
        city = hotel.city
    )
}