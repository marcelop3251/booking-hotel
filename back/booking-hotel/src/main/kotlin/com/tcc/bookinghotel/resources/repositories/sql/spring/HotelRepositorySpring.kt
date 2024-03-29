package com.tcc.bookinghotel.resources.repositories.sql.spring

import com.tcc.bookinghotel.resources.repositories.entities.HotelEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface HotelRepositorySpring : CoroutineCrudRepository<HotelEntity, Int> {

    suspend fun findByOrganizationId(organizationId: Int): HotelEntity?
    suspend fun findAllByCompanyId(companyId: Int): Flow<HotelEntity>
    //suspend fun findByRoomId(roomId: Int): HotelEntity?
}