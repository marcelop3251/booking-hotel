package com.tcc.bookinghotel.application.security

import com.tcc.bookinghotel.application.security.authorization.JWTAuthorizationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.codec.json.AbstractJackson2Decoder
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers
import org.springframework.web.cors.reactive.CorsWebFilter

@Configuration
@EnableWebFluxSecurity
class SpringConfiguration(
    val jwtConfig: TokenService,
) {

    @Bean
    fun springSecurityFilterChain(
        http: ServerHttpSecurity,
        jwtAuthenticationFilter: AuthenticationWebFilter,
        authorizationFilter: JWTAuthorizationFilter,
        corsWebFilter: CorsWebFilter
    ): SecurityWebFilterChain {
        http.csrf().disable()
            .authorizeExchange()
            .pathMatchers(HttpMethod.POST,"/login").permitAll()
            .pathMatchers(HttpMethod.POST, "/customer").permitAll()
            .pathMatchers(HttpMethod.POST, "/company").permitAll()
            .pathMatchers(HttpMethod.GET, "/hotel/services/*").permitAll()
            .pathMatchers("/admin/**").hasAuthority("ADMIN")
            .pathMatchers("/room/*", "/hotel/*").hasAuthority("USER")
            .anyExchange().authenticated()
            .and()
            .cors {
                it.disable()
            }
            .addFilterBefore(corsWebFilter, SecurityWebFiltersOrder.CORS)
            .addFilterBefore(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .addFilterBefore(authorizationFilter, SecurityWebFiltersOrder.AUTHORIZATION)
            .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(userDetailsService: ReactiveUserDetailsService, passwordEncoder: PasswordEncoder): ReactiveAuthenticationManager {
        val manager = UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService)
        manager.setPasswordEncoder(passwordEncoder)
        return manager
    }

    @Bean
    fun authenticationWebFilter(
        reactiveAuthenticationManager: ReactiveAuthenticationManager,
        jwtConverter: ServerAuthenticationConverter,
        serverAuthenticationSuccessHandler: ServerAuthenticationSuccessHandler,
    ): AuthenticationWebFilter {
        val authenticationWebFilter = AuthenticationWebFilter(reactiveAuthenticationManager)
        authenticationWebFilter.setRequiresAuthenticationMatcher {
            ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/login") .matches(it)
        }
        authenticationWebFilter.setServerAuthenticationConverter(jwtConverter)
        authenticationWebFilter.setSecurityContextRepository(NoOpServerSecurityContextRepository.getInstance())
        authenticationWebFilter.setAuthenticationSuccessHandler(serverAuthenticationSuccessHandler)
        return authenticationWebFilter
    }

    @Bean
    fun jacksonDecoder(): AbstractJackson2Decoder = Jackson2JsonDecoder()
}