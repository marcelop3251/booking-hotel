package com.tcc.bookinghotel.domain.exception

class CompanyRegistryException(
    override val type: TypeException,
    override val message: String,
) : BaseException(type, message)