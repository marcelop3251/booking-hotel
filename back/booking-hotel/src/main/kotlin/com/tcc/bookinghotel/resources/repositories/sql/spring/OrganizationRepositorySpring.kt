package com.tcc.bookinghotel.resources.repositories.sql.spring

import com.tcc.bookinghotel.resources.repositories.entities.OrganizationEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrganizationRepositorySpring : CoroutineCrudRepository<OrganizationEntity, Int> {
    suspend fun findByCnpj(cnpj: String): OrganizationEntity?
}
