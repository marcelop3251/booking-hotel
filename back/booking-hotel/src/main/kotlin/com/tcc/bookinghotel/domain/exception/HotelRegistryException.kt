package com.tcc.bookinghotel.domain.exception

class HotelRegistryException(
    val typeException: TypeException,
    override val message: String
): BaseException(typeException, message)