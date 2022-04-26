package com.tcc.bookinghotel.domain.exception

class CustomerRegistryException(
    override val type: TypeException,
    override val message: String,
) : BaseException(type, message)