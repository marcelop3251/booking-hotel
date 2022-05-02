package com.tcc.bookinghotel.domain.repository

import com.tcc.bookinghotel.domain.entity.Company

interface CompanyRepository {

    suspend fun existsByEmail(username: String): Boolean
    suspend fun create(company: Company): Company
}