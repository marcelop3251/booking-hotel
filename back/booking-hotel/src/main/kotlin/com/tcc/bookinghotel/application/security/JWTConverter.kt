package com.tcc.bookinghotel.application.security

import com.tcc.bookinghotel.application.dto.CredentialsRequest
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactor.mono
import org.springframework.core.ResolvableType
import org.springframework.http.MediaType
import org.springframework.http.codec.json.AbstractJackson2Decoder
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class JWTConverter(
    private val jacksonDecoder: AbstractJackson2Decoder
) : ServerAuthenticationConverter {

    override fun convert(exchange: ServerWebExchange): Mono<Authentication>  = mono {
        val body = exchange.request.body
        val type = ResolvableType.forClass(CredentialsRequest::class.java)
        val credentials = jacksonDecoder
            .decodeToMono(body, type, MediaType.APPLICATION_JSON, mapOf())
            .onErrorResume { Mono.empty<CredentialsRequest>() }
            .cast(CredentialsRequest::class.java)
            .awaitFirstOrNull()

        return@mono UsernamePasswordAuthenticationToken(credentials?.username, credentials?.password)
    }
}