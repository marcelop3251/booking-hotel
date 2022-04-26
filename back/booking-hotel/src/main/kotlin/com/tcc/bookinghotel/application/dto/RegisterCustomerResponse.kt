package com.tcc.bookinghotel.application.dto

import com.tcc.bookinghotel.domain.entity.Customer

data class RegisterCustomerResponse(
    val id: Int,
    val name: String,
    val email: String
) {
    constructor(customer: Customer) : this(id = customer.id!!, name = customer.name, email = customer.email)
}