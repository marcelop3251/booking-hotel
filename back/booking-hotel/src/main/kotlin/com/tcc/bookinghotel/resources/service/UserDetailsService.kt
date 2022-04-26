package com.tcc.bookinghotel.resources.service

import com.tcc.bookinghotel.resources.repositories.entities.CredentialEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsService(
    private val credential: CredentialEntity?
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf();
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