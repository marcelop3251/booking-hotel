package com.tcc.bookinghotel.resources.repositories.sql

import com.tcc.bookinghotel.domain.entity.Booking
import com.tcc.bookinghotel.domain.entity.StatusBooking
import com.tcc.bookinghotel.domain.repository.BookingRepository
import com.tcc.bookinghotel.domain.repository.HotelRepository
import com.tcc.bookinghotel.resources.repositories.entities.BookingEntity
import com.tcc.bookinghotel.resources.repositories.sql.spring.BookingRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.HotelRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.RoomRepositorySpring
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Component

@Component
class BookingRepositoryImpl(
    val bookingRepositorySpring: BookingRepositorySpring,
    val hotelRepository: HotelRepository,
) : BookingRepository {

    override suspend fun create(booking: Booking, customerId: Int, roomId: Int): Booking {
        return bookingRepositorySpring.save(BookingEntity(booking, customerId, roomId)).toDomain(booking.hotel!!)
    }

    override suspend fun countByRoomIdAndStatusIn(id: Int, status: List<StatusBooking>): Int {
        return bookingRepositorySpring.countByRoomIdAndStatusIn(id, status.map { it.name })
    }

    override suspend fun findAll(customerId: String): Flow<Booking> {
        return bookingRepositorySpring.findAllByCustomerId(customerId).map {
            val hotel = hotelRepository.findByRoomId(it.roomId)
            it.toDomain(hotel)
        }
    }

    override suspend fun findByCustomerIdAndStatus(customerId: String, statusBooking: StatusBooking): Flow<Booking> {
        return bookingRepositorySpring.findByCustomerIdAndStatus(customerId, statusBooking.name).map { it.toDomain() }
    }
}