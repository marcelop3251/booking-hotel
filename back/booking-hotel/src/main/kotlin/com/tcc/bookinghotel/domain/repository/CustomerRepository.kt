package com.tcc.bookinghotel.domain.repository

import com.tcc.bookinghotel.domain.entity.Customer

interface CustomerRepository {

    suspend fun existsByEmail(username: String): Boolean
    suspend fun create(customer: Customer): Customer

}