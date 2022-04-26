package com.tcc.bookinghotel.resources.repositories.entities

import com.tcc.bookinghotel.domain.entity.Company
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(value = "company")
data class CompanyEntity(
    @Id
    var id: Int? = null,
    val credentialId: Int,
    val organizationId: Int,
) {
    constructor(company: Company, organizationId: Int, credentialId: Int): this(
        id = company.id,
        credentialId = credentialId,
        organizationId = organizationId,
    )

    suspend fun toDomain(organizationEntity: OrganizationEntity, email: String, password: String): Company = Company(
        id = id,
        email = email,
        cnpj = organizationEntity.cnpj,
        name = organizationEntity.name,
        city = organizationEntity.city,
        password = password
    )
}