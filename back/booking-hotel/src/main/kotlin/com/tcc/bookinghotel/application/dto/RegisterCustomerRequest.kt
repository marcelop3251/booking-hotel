package com.tcc.bookinghotel.application.dto

import com.tcc.bookinghotel.domain.entity.Customer

class RegisterCustomerRequest(
    val email: String,
    val name: String,
    val password: String
) {
    fun toDomain() = Customer(null, name, email, password)
}