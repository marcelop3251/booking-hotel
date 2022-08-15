package com.tcc.bookinghotel.resources.repositories.sql.spring

import com.tcc.bookinghotel.resources.repositories.entities.CompanyEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepositorySpring : CoroutineCrudRepository<CompanyEntity, Int> {

    suspend fun findByCredentialId(userBackoffice: Int): CompanyEntity?
}