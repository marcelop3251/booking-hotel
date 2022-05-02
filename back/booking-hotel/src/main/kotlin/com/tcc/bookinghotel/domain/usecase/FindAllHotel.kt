package com.tcc.bookinghotel.domain.usecase

import com.tcc.bookinghotel.domain.entity.Hotel
import com.tcc.bookinghotel.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow
import org.slf4j.LoggerFactory

class FindAllHotel(
    private val hotelRepository: HotelRepository
) {

    val log = LoggerFactory.getLogger(javaClass)

    suspend fun execute(): Flow<Hotel> {
        log.info("Execute usecase findAllHotel")
        return hotelRepository.findAll()
    }
}