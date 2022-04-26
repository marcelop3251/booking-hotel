package com.tcc.bookinghotel.application.dto

class HttpErrorResponse(
    val type: String,
    val message: String,
    val details: Map<String, List<String>> = mapOf()
)