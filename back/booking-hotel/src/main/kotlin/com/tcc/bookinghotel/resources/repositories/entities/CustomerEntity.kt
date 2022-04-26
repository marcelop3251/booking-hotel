package com.tcc.bookinghotel.resources.repositories.entities

import com.tcc.bookinghotel.domain.entity.Customer
import io.azam.ulidj.ULID
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table

@Table(value = "customer")
data class CustomerEntity(
    @Id
    var id: Int?,
    val name: String,
    val credentialId: Int,
) {
    constructor(customer: Customer, credentialId: Int) : this(
        id = customer.id,
        name = customer.name,
        credentialId = credentialId
    )

    fun toDomain(email: String, password: String) = Customer(id, name, email, password)
}