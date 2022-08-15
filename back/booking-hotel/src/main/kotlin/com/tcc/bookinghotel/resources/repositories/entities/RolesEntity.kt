package com.tcc.bookinghotel.resources.repositories.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority

@Table(value = "granted_authority")
data class RolesEntity(
    @Id
    var id: Int?,
    val role: String,
    val credentialId: Int
) : GrantedAuthority {

    override fun getAuthority(): String {
        return role
    }
}