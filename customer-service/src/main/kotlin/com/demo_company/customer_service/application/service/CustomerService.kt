package com.demo_company.customer_service.application.service

import com.demo_company.customer_service.application.command.GetCustomerRequest
import com.demo_company.customer_service.application.port.UseCases
import com.demo_company.customer_service.domain.exception.CustomerExceptions
import com.demo_company.customer_service.domain.model.Customer
import com.demo_company.customer_service.domain.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) : UseCases.GetCustomerUseCase {

    override fun getCustomer(request: GetCustomerRequest): Customer {
        return customerRepository.findById(request.customerId)
            ?: throw CustomerExceptions.CustomerNotFoundException(request.customerId)
    }
}
