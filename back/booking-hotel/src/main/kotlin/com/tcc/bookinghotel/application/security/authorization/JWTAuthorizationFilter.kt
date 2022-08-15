package com.tcc.bookinghotel.application.security.authorization

import com.tcc.bookinghotel.application.security.TokenService
import com.tcc.bookinghotel.resources.repositories.entities.RolesEntity
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class JWTAuthorizationFilter(
    private val tokenService: TokenService
) : WebFilter {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val authHeader = exchange.request.headers.getFirst(HttpHeaders.AUTHORIZATION) ?: return chain.filter(exchange)

        if (!authHeader.startsWith("Bearer ")) {
            return chain.filter(exchange)
        }

        try {
            val token = tokenService.decodeAccessToken(authHeader)
            val role = token["role"] as ArrayList<String>
            val auth = UsernamePasswordAuthenticationToken(token.subject, null, role.map { RolesEntity(null, it, token.subject.toInt())} )
            return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth))
        } catch (e: Exception) {
            logger.error("JWT exception", e)
        }

        return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.clearContext())    }

}