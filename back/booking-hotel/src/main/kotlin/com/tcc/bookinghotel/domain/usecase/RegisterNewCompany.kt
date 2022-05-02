package com.tcc.bookinghotel.domain.usecase

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

    suspend fun execute(company: Company): Company {
        if (companyRepository.existsByEmail(company.email)) {
            throw CompanyRegistryException(TypeException.COMPANY_REGISTRATION, COMPANY_REGISTRTATION_MESSAGE)
        }
        return companyRepository.create(company)
    }
}