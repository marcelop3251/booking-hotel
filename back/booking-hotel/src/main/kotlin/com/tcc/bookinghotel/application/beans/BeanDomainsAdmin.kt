package com.tcc.bookinghotel.application.beans

import com.tcc.bookinghotel.domain.repository.BookingRepository
import com.tcc.bookinghotel.domain.usecase.admin.FindAllBookingAdminByStatus
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class BeanDomainsAdmin {

    @Bean
    fun findAllBookingAdmin(bookingRepository: BookingRepository) = FindAllBookingAdminByStatus(bookingRepository)

}