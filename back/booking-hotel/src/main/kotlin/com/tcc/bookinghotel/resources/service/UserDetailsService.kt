package com.tcc.bookinghotel.resources.service

import com.tcc.bookinghotel.resources.repositories.entities.CredentialEntity
import com.tcc.bookinghotel.resources.repositories.entities.RolesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsService(
    private val credential: CredentialEntity?,
    private val rolesEntity: List<RolesEntity>
) : UserDetails  {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return rolesEntity.toMutableList()
    }

    override fun getPassword(): String? {
       return credential?.password
    }

    override fun getUsername(): String? {
        return credential?.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true;
    }

    override fun isAccountNonLocked(): Boolean {
       return true;
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun getId() = credential?.id
}