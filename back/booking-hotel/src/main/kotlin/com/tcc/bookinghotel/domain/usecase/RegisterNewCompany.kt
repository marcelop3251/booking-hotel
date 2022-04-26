package com.tcc.bookinghotel.domain.usecase

import com.newrelic.api.agent.Trace
import com.tcc.bookinghotel.domain.entity.Company
import com.tcc.bookinghotel.domain.exception.CompanyRegistryException
import com.tcc.bookinghotel.domain.exception.TypeException
import com.tcc.bookinghotel.domain.repository.CompanyRepository
import org.springframework.stereotype.Component

private const val COMPANY_REGISTRTATION_MESSAGE = "Company already registred"

@Component
class RegisterNewCompany(
    private val companyRepository: CompanyRepository
) {

    @Trace(async = true)
    suspend fun execute(company: Company): Company {
        val result = companyRepository.findByEmail(company.email)
        if (result != null) {
            throw CompanyRegistryException(TypeException.COMPANY_REGISTRATION, COMPANY_REGISTRTATION_MESSAGE)
        }
        return companyRepository.create(company)
    }
}