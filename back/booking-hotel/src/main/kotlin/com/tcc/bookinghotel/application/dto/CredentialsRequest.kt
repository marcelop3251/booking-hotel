package com.tcc.bookinghotel.application.dto

//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

data class CredentialsRequest(
    val username: String = "",
    val password: String = ""
) {
   // fun converter() = UsernamePasswordAuthenticationToken(username, password)
}
