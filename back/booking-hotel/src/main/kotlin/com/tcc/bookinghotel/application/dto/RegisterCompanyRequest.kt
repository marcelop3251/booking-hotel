package com.tcc.bookinghotel.application.dto

import com.tcc.bookinghotel.domain.entity.Company

class RegisterCompanyRequest(
    val email: String,
    val name: String,
    val password: String,
    val cnpj: String,
    val city: String,
) {
    suspend fun toCompany(): Company = Company(
        id = null,
        name = name,
        password = password,
        cnpj = cnpj,
        email = email,
        city = city,
    )
}