package com.tcc.bookinghotel.resources.repositories.sql

import com.tcc.bookinghotel.domain.entity.Customer
import com.tcc.bookinghotel.domain.repository.CustomerRepository
import com.tcc.bookinghotel.resources.repositories.entities.CredentialEntity
import com.tcc.bookinghotel.resources.repositories.entities.CustomerEntity
import com.tcc.bookinghotel.resources.repositories.sql.spring.CredentialRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.CustomerRepositorySpring
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CustomerRepositoryImpl(
    val customerRepositorySpring: CustomerRepositorySpring,
    val bCrypt: PasswordEncoder,
    val credentialRepositorySpring: CredentialRepositorySpring
) : CustomerRepository {

    val logger = LoggerFactory.getLogger(javaClass)

    override suspend fun existsByEmail(username: String) =
        credentialRepositorySpring.existsByEmail(username)

    @Transactional
    override suspend fun create(customer: Customer) =
        credentialRepositorySpring.save(CredentialEntity(customer, bCrypt)).let {
            customerRepositorySpring.save(CustomerEntity(customer, it.id!!))
                .toDomain(customer.email, it.password)
                .also {
                    logger.info("Client created with success")
                }
        }

}