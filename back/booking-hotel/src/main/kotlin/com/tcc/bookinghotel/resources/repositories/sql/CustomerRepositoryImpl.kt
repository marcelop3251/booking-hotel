package com.tcc.bookinghotel.resources.repositories.sql

import com.tcc.bookinghotel.domain.entity.Customer
import com.tcc.bookinghotel.domain.exception.InconsistentDataException
import com.tcc.bookinghotel.domain.exception.TypeException
import com.tcc.bookinghotel.domain.repository.CustomerRepository
import com.tcc.bookinghotel.resources.repositories.entities.CredentialEntity
import com.tcc.bookinghotel.resources.repositories.entities.CustomerEntity
import com.tcc.bookinghotel.resources.repositories.entities.RolesEntity
import com.tcc.bookinghotel.resources.repositories.sql.spring.CredentialRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.CustomerRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.RolesRepositorySpring
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CustomerRepositoryImpl(
    val customerRepositorySpring: CustomerRepositorySpring,
    val bCrypt: PasswordEncoder,
    val credentialRepositorySpring: CredentialRepositorySpring,
    val rolesRepositorySpring: RolesRepositorySpring
) : CustomerRepository {

    val logger = LoggerFactory.getLogger(javaClass)

    override suspend fun existsByEmail(username: String) =
        credentialRepositorySpring.existsByEmail(username)

    @Transactional
    override suspend fun create(customer: Customer) =
        credentialRepositorySpring.save(CredentialEntity(customer, bCrypt)).let { credential ->
            customerRepositorySpring.save(CustomerEntity(customer, credential.id!!))
                .toDomain(customer.email, credential.password)
                .also {
                    rolesRepositorySpring.save(RolesEntity(null, "USER",credential.id!!))
                }
                .also {
                    logger.info("Client created with success")
                }
        }

    override suspend fun findById(id: Int): Customer {
        return credentialRepositorySpring.findById(id)?.let {
            customerRepositorySpring.findByCredentialId(it.id!!)?.toDomain(it.email, "secret")
        } ?: throw InconsistentDataException(TypeException.CUSTOMER_REGISTRATION, "Customer not found")
    }
}