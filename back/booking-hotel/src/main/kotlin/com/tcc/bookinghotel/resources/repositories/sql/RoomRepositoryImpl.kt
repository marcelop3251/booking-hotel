package com.tcc.bookinghotel.resources.repositories.sql

import com.tcc.bookinghotel.domain.entity.Room
import com.tcc.bookinghotel.domain.repository.RoomRepository
import com.tcc.bookinghotel.resources.repositories.entities.RoomEntity
import com.tcc.bookinghotel.resources.repositories.sql.spring.RoomRepositorySpring
import org.springframework.stereotype.Component

@Component
class RoomRepositoryImpl(
    private val roomRepositorySpring: RoomRepositorySpring
) : RoomRepository {

    override suspend fun create(room: Room, hotelId: Int): Room {
        return roomRepositorySpring.save(RoomEntity(room, hotelId)).toDomain()
    }

    override suspend fun findById(id: Int): Room? {
        return roomRepositorySpring.findById(id)?.toDomain()
    }

    override suspend fun getTotalBedrooms(hotelId: Int): Int? {
        return roomRepositorySpring.sumByHotelId(hotelId)
    }


}