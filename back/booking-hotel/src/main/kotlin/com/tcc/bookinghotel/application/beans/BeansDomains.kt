package com.tcc.bookinghotel.application.beans

import com.tcc.bookinghotel.domain.repository.HotelRepository
import com.tcc.bookinghotel.domain.repository.RoomRepository
import com.tcc.bookinghotel.domain.usecase.RegisterNewHotel
import com.tcc.bookinghotel.domain.usecase.RegisterNewRoom
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class BeansDomains {

    @Bean
    fun registerNewHotel(hotelRepository: HotelRepository) = RegisterNewHotel(hotelRepository)

    @Bean
    fun registerNewRoom(roomRepository: RoomRepository, hotelRepository: HotelRepository) = RegisterNewRoom(roomRepository, hotelRepository)
}