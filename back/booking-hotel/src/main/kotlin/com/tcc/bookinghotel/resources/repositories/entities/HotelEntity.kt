package com.tcc.bookinghotel.resources.repositories.entities

import com.tcc.bookinghotel.domain.entity.Hotel
import io.azam.ulidj.ULID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("hotel")
data class HotelEntity(
    @Id
    var id: Int?,
    val numberBedrooms: Int,
    val organizationId: Int,
    val companyId: Int
) {
    constructor(hotel: Hotel, organizationId: Int, companyId: Int) : this(
        id = hotel.id,
        numberBedrooms = hotel.numberBedrooms,
        organizationId = organizationId,
        companyId = companyId
    )

    fun toDomain(organizationEntity: OrganizationEntity) = Hotel(
        id = id,
        numberBedrooms = numberBedrooms,
        name = organizationEntity.name,
        cnpj = organizationEntity.cnpj,
        city = organizationEntity.city
    )

    fun toDomain(organizationEntity: OrganizationEntity, roomEntity: List<RoomEntity>) = Hotel(
        id = id,
        numberBedrooms = numberBedrooms,
        name = organizationEntity.name,
        cnpj = organizationEntity.cnpj,
        city = organizationEntity.city,
        room = roomEntity.map { it.toDomain() }
    )

}