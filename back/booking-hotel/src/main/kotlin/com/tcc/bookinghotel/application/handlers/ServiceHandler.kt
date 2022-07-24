package com.tcc.bookinghotel.application.handlers

import com.tcc.bookinghotel.application.dto.ServiceRequest
import com.tcc.bookinghotel.domain.usecase.CreateService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class ServiceHandler(
    private val createService: CreateService
) {
    suspend fun createService(request: ServerRequest): ServerResponse {
        val service = request.awaitBody<ServiceRequest>()
        return ServerResponse.ok().bodyValueAndAwait(createService.execute(service.toDomain()))
    }
}