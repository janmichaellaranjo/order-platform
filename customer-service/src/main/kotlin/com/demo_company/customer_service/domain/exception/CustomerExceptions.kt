package com.demo_company.customer_service.domain.exception

sealed class CustomerExceptions(message: String) : RuntimeException(message) {

    class CustomerNotFoundException(customerId: String) :
        CustomerExceptions("Customer $customerId not found")
}
