package com.tcc.bookinghotel.domain.exception

open class BaseException (
    open val type: TypeException,
    override val message: String,
): RuntimeException(message)