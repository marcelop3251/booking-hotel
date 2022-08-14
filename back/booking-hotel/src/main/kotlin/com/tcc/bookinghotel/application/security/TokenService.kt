package com.tcc.bookinghotel.application.security

import com.tcc.bookinghotel.resources.service.UserDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class TokenService {

    private val expiration: Long = 3600000

    @Value("\${jwt.secret}")
    private lateinit var jwtSecret: String

    fun generateToken(authentication: Authentication, roles: Array<String>): String {
        val loggedUder = authentication.principal as UserDetailsService
        return Jwts.builder()
            .setIssuer("API of booking hotel")
            .setSubject(loggedUder.getId().toString())
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .claim("role", roles)
            .signWith(SignatureAlgorithm.HS512, jwtSecret.toByteArray())
            .compact()
    }

    fun isValid(jwt: String): Boolean {
        return try {
            Jwts.parser().setSigningKey(jwtSecret.toByteArray()).parseClaimsJws(jwt)
            true
        } catch (ex: Exception) {
            false
        }
    }

    fun getAuthentication(jwt: String): Authentication {
        val username = Jwts.parser().setSigningKey(jwtSecret.toByteArray()).parseClaimsJws(jwt).body.subject
        return UsernamePasswordAuthenticationToken(username, null)

    }

    fun getIdCust(token: String?): String {
        val claims = Jwts.parser().setSigningKey(jwtSecret.toByteArray()).parseClaimsJws(token).body
        return claims.subject
    }

    fun decodeAccessToken(token: String): Claims {
        val tokenClear = token.replace("Bearer ", "")
        if (isValid(tokenClear)) {
            return Jwts.parser().setSigningKey(jwtSecret.toByteArray())
                .parseClaimsJws(tokenClear).body
        } else {
            throw IllegalArgumentException("Token is invalid")
        }
    }
}