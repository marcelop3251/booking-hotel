package com.tcc.bookinghotel.domain.exception

class CompanyRegistryException(
    val type: TypeException,
    override val message: String,
) : RuntimeException(message)