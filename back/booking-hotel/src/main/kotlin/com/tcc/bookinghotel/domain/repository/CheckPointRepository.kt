package com.tcc.bookinghotel.domain.repository

interface CheckPointRepository {

    suspend fun notExistCheckPoint(customerId: Int, bookingId: Int?, hotelId: Int?): Boolean
}