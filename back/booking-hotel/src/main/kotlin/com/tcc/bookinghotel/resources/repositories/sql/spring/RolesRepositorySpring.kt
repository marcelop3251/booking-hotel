package com.tcc.bookinghotel.resources.repositories.sql.spring

import com.tcc.bookinghotel.resources.repositories.entities.RolesEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RolesRepositorySpring : CoroutineCrudRepository<RolesEntity, Int> {

    suspend fun findByCredentialId(credentialId: Int): Flow<RolesEntity>
}