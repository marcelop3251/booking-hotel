package com.tcc.bookinghotel.application.security.authentication

import com.tcc.bookinghotel.application.security.TokenService
import com.tcc.bookinghotel.resources.repositories.sql.spring.CredentialRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.CustomerRepositorySpring
import kotlinx.coroutines.runBlocking
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class AuthenticationTokenFilter(
    val tokenService: TokenService,
    val repository: CredentialRepositorySpring
) : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val token = getToken(exchange)

        if (token != null && tokenService.isValid(token)) {
            authenticateCustomer(token)
        }
        return chain.filter(exchange)
    }

    private fun authenticateCustomer(token: String?) {
        val cutomerId = tokenService.getIdCust(token)
        val credential = runBlocking {
            repository.findById(cutomerId.toInt())
        }
        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(credential!!.id, null, null)
    }


    private fun getToken(exchange: ServerWebExchange): String? {
        val token = exchange.request.headers["Authorization"]?.get(0)
        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }
        return token.substring(7, token.length)
    }
}