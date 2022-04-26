package com.tcc.bookinghotel.application.handlers

import com.newrelic.api.agent.Trace
import com.tcc.bookinghotel.application.dto.RegisterCustomerRequest
import com.tcc.bookinghotel.application.dto.RegisterCustomerResponse
import com.tcc.bookinghotel.domain.usecase.RegisterNewCustomer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class RegisterCustomerHandler(
    val registerNewCustomer: RegisterNewCustomer
) {

    @Trace
    suspend fun registerCustomer(request: ServerRequest): ServerResponse {
        val registerCustomer = request.awaitBody<RegisterCustomerRequest>()
        val customer = registerNewCustomer.execute(registerCustomer.toDomain())
        return ServerResponse.ok().bodyValueAndAwait(RegisterCustomerResponse(customer))
    }
}