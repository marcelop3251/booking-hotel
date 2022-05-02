package com.tcc.bookinghotel.resources.repositories.sql.spring

import com.tcc.bookinghotel.resources.repositories.entities.RoomEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomRepositorySpring : CoroutineCrudRepository<RoomEntity, Int> {

    suspend fun findByHotelId(hotelId: Int): Flow<RoomEntity>
}