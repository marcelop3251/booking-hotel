package com.tcc.bookinghotel.domain.exception

class RoomUnavailableException(
    override val type: TypeException,
    override val message: String,
) : BaseException(type, message)