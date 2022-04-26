package com.tcc.bookinghotel.resources.repositories.sql

import com.tcc.bookinghotel.domain.entity.Company
import com.tcc.bookinghotel.domain.repository.CompanyRepository
import com.tcc.bookinghotel.resources.repositories.entities.CompanyEntity
import com.tcc.bookinghotel.resources.repositories.entities.CredentialEntity
import com.tcc.bookinghotel.resources.repositories.entities.OrganizationEntity
import com.tcc.bookinghotel.resources.repositories.sql.spring.CompanyRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.CredentialRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.OrganizationRepositorySpring
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CompanyRepositoryImpl(
    val companyRepositorySpring: CompanyRepositorySpring,
    val organizationRepositorySpring: OrganizationRepositorySpring,
    val credentialRepositorySpring: CredentialRepositorySpring,
    val bCrypt: PasswordEncoder,
) : CompanyRepository {

    override suspend fun findByEmail(email: String): Company? {
        return credentialRepositorySpring.findByEmail(email)?.let { credential ->
            companyRepositorySpring.findByCredentialId(credential.id!!)?.let { company ->
                organizationRepositorySpring.findById(company.organizationId)?.let { organizationEntity ->
                    company.toDomain(organizationEntity, credential.email, credential.password)
                }
            }
        }
    }

    @Transactional
    override suspend fun create(company: Company): Company {
        return credentialRepositorySpring.save(CredentialEntity(company, bCrypt)).let { credential ->
            organizationRepositorySpring.save(OrganizationEntity(company)).let { organization ->
                companyRepositorySpring
                    .save(CompanyEntity(company, organization.id!!, credential.id!!))
                    .toDomain(organization, credential.email, credential.password)
            }
        }
    }

}