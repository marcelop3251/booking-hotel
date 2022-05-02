package com.tcc.bookinghotel.resources.repositories.sql.spring

import com.tcc.bookinghotel.resources.repositories.entities.CredentialEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CredentialRepositorySpring : CoroutineCrudRepository<CredentialEntity, Int> {
    suspend fun existsByEmail(email: String): Boolean
    suspend fun findByEmail(email: String): CredentialEntity
}