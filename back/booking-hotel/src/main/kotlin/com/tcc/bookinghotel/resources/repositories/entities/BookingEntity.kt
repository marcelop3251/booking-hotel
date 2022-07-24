package com.tcc.bookinghotel.resources.repositories.entities

import com.tcc.bookinghotel.domain.entity.Booking
import com.tcc.bookinghotel.domain.entity.Customer
import com.tcc.bookinghotel.domain.entity.Hotel
import com.tcc.bookinghotel.domain.entity.StatusBooking
import java.time.LocalDate
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(value= "booking")
data class BookingEntity(
    @Id
    var id: Int?,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val description: String ?= "",
    val status: String,
    val customerId: Int,
    val roomId: Int
) {
    constructor(booking: Booking, customerId: Int, roomId: Int): this (
        id = booking.id,
        startDate = booking.startDate,
        endDate = booking.endDate,
        description = booking.description,
        status = booking.status.name,
        customerId = customerId,
        roomId =  roomId, // TODO ADICIONAR TRATAMENTO para os casos de id null
    )

    fun toDomain(hotel: Hotel? = null, customer: Customer? = null) = Booking(
        id = id,
        startDate = startDate,
        endDate = endDate,
        description = description!!,
        status = StatusBooking.valueOf(status),
        hotel = hotel,
        customer = customer
    )
}