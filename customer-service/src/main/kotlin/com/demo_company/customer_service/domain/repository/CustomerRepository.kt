package com.demo_company.customer_service.domain.repository

import com.demo_company.customer_service.domain.model.Customer

interface CustomerRepository {
    fun findById(customerId: String): Customer?
    fun save(customer: Customer): Customer
}
