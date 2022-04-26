package com.tcc.bookinghotel.domain.entity

data class Customer(
    val id: Int? = null,
    val name: String,
    override val email: String,
    override val password: String
): User
