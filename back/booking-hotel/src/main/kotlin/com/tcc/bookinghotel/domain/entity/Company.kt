package com.tcc.bookinghotel.domain.entity

data class Company(
    val id: Int? = null,
    override val email: String,
    override val password: String,
    override val name: String,
    override val cnpj: String,
    override val city: String,
) : Organization(name = name, cnpj = cnpj, city = city), User