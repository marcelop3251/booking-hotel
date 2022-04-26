package com.tcc.bookinghotel.resources.service

import com.tcc.bookinghotel.resources.repositories.sql.spring.CredentialRepositorySpring
import kotlinx.coroutines.reactor.mono
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(
    val repository: CredentialRepositorySpring
) : ReactiveUserDetailsService {

    override fun findByUsername(username: String?): Mono<UserDetails>  = mono {
        UserDetailsService(repository.findByEmail(username))
    }
}