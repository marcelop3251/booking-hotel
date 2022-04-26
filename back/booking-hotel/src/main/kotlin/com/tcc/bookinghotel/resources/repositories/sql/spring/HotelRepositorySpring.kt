package com.tcc.bookinghotel.resources.repositories.sql.spring

import com.tcc.bookinghotel.resources.repositories.entities.HotelEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface HotelRepositorySpring : CoroutineCrudRepository<HotelEntity, Int> {

    suspend fun findByOrganizationId(organizationId: Int): HotelEntity?
}