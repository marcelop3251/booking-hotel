package com.tcc.bookinghotel.domain.exception

class NotFoundItemException(
    override val type: TypeException,
    override val message: String,
) : BaseException(type, message)