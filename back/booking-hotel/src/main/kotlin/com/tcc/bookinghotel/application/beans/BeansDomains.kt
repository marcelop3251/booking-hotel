package com.tcc.bookinghotel.application.beans

import com.tcc.bookinghotel.domain.repository.BookingRepository
import com.tcc.bookinghotel.domain.repository.HotelRepository
import com.tcc.bookinghotel.domain.repository.RoomRepository
import com.tcc.bookinghotel.domain.usecase.BookingHotel
import com.tcc.bookinghotel.domain.usecase.FindAllBooking
import com.tcc.bookinghotel.domain.usecase.FindAllHotel
import com.tcc.bookinghotel.domain.usecase.FindHoteByRoomId
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

    @Bean
    fun registerFindAllHotel(hotelRepository: HotelRepository) = FindAllHotel(hotelRepository)

    @Bean
    fun findHotelByRoomId(hotelRepository: HotelRepository) = FindHoteByRoomId(hotelRepository)

    @Bean
    fun bookingHotel(bookingRepository: BookingRepository, roomRepository: RoomRepository) = BookingHotel(bookingRepository, roomRepository)

    @Bean
    fun findAllBooking(bookingRepository: BookingRepository) = FindAllBooking(bookingRepository)
}