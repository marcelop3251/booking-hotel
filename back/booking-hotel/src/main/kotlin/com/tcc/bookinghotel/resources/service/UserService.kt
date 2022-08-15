package com.tcc.bookinghotel.resources.service

import com.tcc.bookinghotel.resources.repositories.sql.spring.CredentialRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.RolesRepositorySpring
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.mono
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(
    val repository: CredentialRepositorySpring,
    val rolesRepositorySpring: RolesRepositorySpring
) : ReactiveUserDetailsService  {

    override fun findByUsername(username: String): Mono<UserDetails>  = mono {
        val credential = repository.findByEmail(username)
        UserDetailsService(credential, rolesRepositorySpring.findByCredentialId(credential.id!!).toList())
    }
}