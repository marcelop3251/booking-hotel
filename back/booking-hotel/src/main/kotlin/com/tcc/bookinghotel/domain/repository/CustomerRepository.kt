package com.tcc.bookinghotel.domain.repository

import com.tcc.bookinghotel.domain.entity.Customer

interface CustomerRepository {

    suspend fun findByEmail(username: String?): Customer?
    suspend fun create(customer: Customer): Customer

}