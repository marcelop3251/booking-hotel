package com.tcc.bookinghotel.application.security.authentication

import com.tcc.bookinghotel.application.security.TokenService
import com.tcc.bookinghotel.resources.service.UserDetailsService
import kotlinx.coroutines.reactor.mono
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.WebFilterExchange
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JWTServerAuthenticationSuccessHandler(
    private val tokenService: TokenService
) : ServerAuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        webFilterExchange: WebFilterExchange?,
        authentication: Authentication?
    ): Mono<Void> = mono {
        val principal = authentication?.principal ?: throw IllegalArgumentException("Not authorized")

        when (principal) {
            is UserDetailsService -> {
                val roles = principal.authorities.map { it.authority }.toTypedArray()
                val accessToken = tokenService.generateToken(authentication, roles)
                webFilterExchange?.exchange?.response?.headers?.set("Authorization", accessToken)
            }
        }
        return@mono null
    }
}