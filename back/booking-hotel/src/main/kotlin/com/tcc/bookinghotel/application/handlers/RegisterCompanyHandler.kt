package com.tcc.bookinghotel.application.handlers


import com.newrelic.api.agent.NewRelic
import com.tcc.bookinghotel.application.dto.RegisterCompanyRequest
import com.tcc.bookinghotel.domain.usecase.RegisterNewCompany
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class RegisterCompanyHandler(
    private val registerNewCompany: RegisterNewCompany
) {
    suspend fun register(serverRequest: ServerRequest) : ServerResponse {
        val companyRequest = serverRequest.awaitBody<RegisterCompanyRequest>()
        val result = registerNewCompany.execute(companyRequest.toCompany())
        return ServerResponse.ok().bodyValueAndAwait(result)
    }
}
