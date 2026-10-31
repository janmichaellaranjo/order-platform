package com.demo_company.customer_service.application.port

import com.demo_company.customer_service.application.command.GetCustomerRequest
import com.demo_company.customer_service.domain.model.Customer

interface UseCases {

    interface GetCustomerUseCase {
        fun getCustomer(request: GetCustomerRequest): Customer
    }
}
