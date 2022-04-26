package com.tcc.bookinghotel.domain.exception

class InconsistentDataException(
    val typeException: TypeException,
    override val message: String
) : BaseException(typeException, message) {
}