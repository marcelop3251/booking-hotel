package com.tcc.bookinghotel.application.security.authentication

import com.tcc.bookinghotel.application.security.TokenService
import com.tcc.bookinghotel.resources.repositories.sql.spring.CredentialRepositorySpring
import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpHeaders
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
            val customerId = authenticateCustomer(token)
            putCustomerInHeader(exchange, customerId)
        }
        return chain.filter(exchange)
    }

    private fun putCustomerInHeader(exchange: ServerWebExchange, customerId: String) {
        val request = exchange.request.mutate().header("x-customer-id", customerId).build()
        exchange.mutate().request(request).build()
    }

    private fun authenticateCustomer(token: String?): String {
        val customerId = tokenService.getIdCust(token)
        val credential = runBlocking {
            repository.findById(customerId.toInt())
        }
        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(credential!!.id, null, null)
        return customerId
    }


    private fun getToken(exchange: ServerWebExchange): String? {
        val token = exchange.request.headers["Authorization"]?.get(0)
        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }
        return token.substring(7, token.length)
    }
}