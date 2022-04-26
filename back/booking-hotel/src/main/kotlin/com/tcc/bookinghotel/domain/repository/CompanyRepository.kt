package com.tcc.bookinghotel.domain.repository

import com.tcc.bookinghotel.domain.entity.Company

interface CompanyRepository {

    suspend fun findByEmail(username: String): Company?
    suspend fun create(company: Company): Company
}