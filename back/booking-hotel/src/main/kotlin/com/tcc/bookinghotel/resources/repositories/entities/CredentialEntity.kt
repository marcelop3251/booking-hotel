package com.tcc.bookinghotel.resources.repositories.entities

import com.tcc.bookinghotel.domain.entity.User
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.crypto.password.PasswordEncoder

@Table(value = "credential")
data class CredentialEntity(
    @Id
    var id: Int? = null,
    val email: String,
    val password: String,
) {
    constructor(user: User, bCrypt: PasswordEncoder ) : this (
        email = user.email,
        password = bCrypt.encode(user.password)
    )
}