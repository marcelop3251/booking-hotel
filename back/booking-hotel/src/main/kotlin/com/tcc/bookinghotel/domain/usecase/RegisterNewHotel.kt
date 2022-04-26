package com.tcc.bookinghotel.domain.usecase

import com.tcc.bookinghotel.domain.entity.Hotel
import com.tcc.bookinghotel.domain.exception.HotelRegistryException
import com.tcc.bookinghotel.domain.exception.TypeException
import com.tcc.bookinghotel.domain.repository.HotelRepository

private const val HOTEL_ALREADY_MESSAGE = "Hotel already registry"

class RegisterNewHotel(
    val hotelRepository: HotelRepository
)  {

    suspend fun execute(hotel: Hotel, companyId: Int): Hotel =
        hotelRepository.findByCNPJ(hotel.cnpj)?.let {
            throw HotelRegistryException(TypeException.HOTEL_ALREADY_REGISTRED, HOTEL_ALREADY_MESSAGE)
        } ?: hotelRepository.create(hotel, companyId)
}