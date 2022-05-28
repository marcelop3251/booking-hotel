package com.tcc.bookinghotel.domain.usecase

import com.tcc.bookinghotel.domain.entity.Customer
import com.tcc.bookinghotel.domain.exception.CustomerRegistryException
import com.tcc.bookinghotel.domain.exception.TypeException
import com.tcc.bookinghotel.domain.repository.CustomerRepository
import org.springframework.stereotype.Component

@Component
class RegisterNewCustomer(
    private val customerRepository: CustomerRepository,
) {

    suspend fun execute(customer: Customer): Customer {
        if (customerRepository.existsByEmail(customer.email)) {
            throw CustomerRegistryException(TypeException.CUSTOMER_REGISTRATION, "CLIENT ALREADY REGISTRED")
        }
        return customerRepository.create(customer)
    }
}