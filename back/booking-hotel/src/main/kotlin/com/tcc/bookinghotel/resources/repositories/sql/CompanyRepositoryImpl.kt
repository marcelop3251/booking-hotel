package com.tcc.bookinghotel.resources.repositories.sql

import com.tcc.bookinghotel.domain.entity.Company
import com.tcc.bookinghotel.domain.repository.CompanyRepository
import com.tcc.bookinghotel.resources.repositories.entities.CompanyEntity
import com.tcc.bookinghotel.resources.repositories.entities.CredentialEntity
import com.tcc.bookinghotel.resources.repositories.entities.OrganizationEntity
import com.tcc.bookinghotel.resources.repositories.entities.RolesEntity
import com.tcc.bookinghotel.resources.repositories.sql.spring.CompanyRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.CredentialRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.OrganizationRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.RolesRepositorySpring
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CompanyRepositoryImpl(
    val companyRepositorySpring: CompanyRepositorySpring,
    val organizationRepositorySpring: OrganizationRepositorySpring,
    val credentialRepositorySpring: CredentialRepositorySpring,
    val bCrypt: PasswordEncoder,
    val rolesRepositorySpring: RolesRepositorySpring
) : CompanyRepository {

    override suspend fun existsByEmail(email: String): Boolean {
        return credentialRepositorySpring.existsByEmail(email)
    }

    @Transactional
    override suspend fun create(company: Company): Company {
        return credentialRepositorySpring.save(CredentialEntity(company, bCrypt)).let { credential ->
            organizationRepositorySpring.save(OrganizationEntity(company)).let { organization ->
                companyRepositorySpring
                    .save(CompanyEntity(company, organization.id!!, credential.id!!))
                    .toDomain(organization, credential.email, credential.password)
                    .apply {
                        rolesRepositorySpring.save(RolesEntity(null, "ADMIN", credential.id!!))
                    }
            }
        }
    }

}