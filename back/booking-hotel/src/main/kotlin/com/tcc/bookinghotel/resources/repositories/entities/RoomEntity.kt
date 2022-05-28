package com.tcc.bookinghotel.resources.repositories.entities

import com.tcc.bookinghotel.domain.entity.Room
import java.math.BigDecimal
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("room")
data class RoomEntity(
    @Id
    var id: Int?,
    val type: String,
    val hotelId: Int,
    val rentValue: String,
    val quantity: Int,
    val description: String
) {
    constructor(room: Room, hotelId: Int): this(
        id = room.id,
        type = room.type,
        hotelId = hotelId,
        rentValue = room.rentValue.toString(),
        quantity = room.quantity,
        description = room.description
    )

    fun toDomain(): Room = Room(
        id = id,
        type = type,
        rentValue = BigDecimal(rentValue),
        quantity = quantity,
        description = description
    )
}