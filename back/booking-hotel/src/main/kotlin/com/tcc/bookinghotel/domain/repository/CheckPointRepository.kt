package com.tcc.bookinghotel.domain.repository

interface CheckPointRepository {

    suspend fun notExistCheckPoint(customerId: String, bookingId: Int?, hotelId: Int?): Boolean
}