package com.tcc.bookinghotel.application.config

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@Component
class Configurations {

    @Bean
    fun addCors(): CorsWebFilter {
        val corsConfig = CorsConfiguration()

        corsConfig.allowedMethods = listOf("POST","OPTIONS", "GET", "DELETE", "PUT")
        corsConfig.allowedOrigins = listOf("http://localhost:3000")
        corsConfig.maxAge = 5
        corsConfig.allowedHeaders = listOf("x-requested-with", "authorization", "Content-Type", "Authorization", "access-control-allow-methods", "access-control-allow-origin")
        corsConfig.exposedHeaders = listOf("Authorization")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfig)
        return CorsWebFilter(source)
    }
}