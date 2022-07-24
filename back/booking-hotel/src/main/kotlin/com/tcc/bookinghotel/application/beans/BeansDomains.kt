package com.tcc.bookinghotel.application.beans

import com.tcc.bookinghotel.domain.repository.BookingRepository
import com.tcc.bookinghotel.domain.repository.CheckPointRepository
import com.tcc.bookinghotel.domain.repository.HotelRepository
import com.tcc.bookinghotel.domain.repository.RequestServiceRepository
import com.tcc.bookinghotel.domain.repository.RoomRepository
import com.tcc.bookinghotel.domain.repository.ServiceRepository
import com.tcc.bookinghotel.domain.usecase.BookingHotel
import com.tcc.bookinghotel.domain.usecase.CreateRequestService
import com.tcc.bookinghotel.domain.usecase.CreateService
import com.tcc.bookinghotel.domain.usecase.FindAllBooking
import com.tcc.bookinghotel.domain.usecase.FindAllHotel
import com.tcc.bookinghotel.domain.usecase.FindAllRequestServices
import com.tcc.bookinghotel.domain.usecase.FindAllServices
import com.tcc.bookinghotel.domain.usecase.FindBookingPendingCheckIn
import com.tcc.bookinghotel.domain.usecase.FindBookingPendingCheckOut
import com.tcc.bookinghotel.domain.usecase.FindHoteByRoomId
import com.tcc.bookinghotel.domain.usecase.RegisterNewHotel
import com.tcc.bookinghotel.domain.usecase.RegisterNewRoom
import com.tcc.bookinghotel.domain.usecase.UpdateStatusBooking
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
    fun findAllBooking(bookingRepository: BookingRepository, hotelRepository: HotelRepository) = FindAllBooking(bookingRepository, hotelRepository)

    @Bean
    fun findBookingPendingCheckIn(
        bookingRepository: BookingRepository,
        checkPointRepository: CheckPointRepository
    ) = FindBookingPendingCheckIn(bookingRepository, checkPointRepository)

    @Bean
    fun findBookingPendingCheckOut(
        bookingRepository: BookingRepository,
        checkPointRepository: CheckPointRepository
    ) = FindBookingPendingCheckOut(bookingRepository, checkPointRepository)

    @Bean
    fun findAllServices(serviceRepository: ServiceRepository) = FindAllServices(serviceRepository)

    @Bean
    fun findAllRequestServices(requestServiceRepository: RequestServiceRepository) = FindAllRequestServices(requestServiceRepository)

    @Bean
    fun createRequestService(
        requestServiceRepository: RequestServiceRepository,
        bookingRepository: BookingRepository,
        serviceRepository: ServiceRepository
    ) = CreateRequestService(requestServiceRepository, bookingRepository, serviceRepository)

    @Bean
    fun createService(serviceRepository: ServiceRepository) = CreateService(serviceRepository)

    @Bean
    fun createUpdateStatusBooking(bookingRepository: BookingRepository) = UpdateStatusBooking(bookingRepository)
}