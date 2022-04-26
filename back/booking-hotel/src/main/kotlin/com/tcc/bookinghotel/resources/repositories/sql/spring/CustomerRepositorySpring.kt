package com.tcc.bookinghotel.resources.repositories.sql.spring

import com.tcc.bookinghotel.resources.repositories.entities.CustomerEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepositorySpring : CoroutineCrudRepository<CustomerEntity, Int> {
    suspend fun findByCredentialId(credentialId: Int): CustomerEntity?
}