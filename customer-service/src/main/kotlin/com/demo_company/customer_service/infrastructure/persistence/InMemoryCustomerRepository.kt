package com.demo_company.customer_service.infrastructure.persistence

import com.demo_company.customer_service.domain.model.Customer
import com.demo_company.customer_service.domain.model.ShippingAddress
import com.demo_company.customer_service.domain.repository.CustomerRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class InMemoryCustomerRepository : CustomerRepository {

    private val customers = ConcurrentHashMap<String, Customer>().apply {
        val seed = Customer(
            customerId = "customer-1",
            name = "Jane Doe",
            shippingAddress = ShippingAddress(
                street = "123 Main St",
                city = "Springfield",
                state = "IL",
                zip = "62701",
                country = "US"
            )
        )
        put(seed.customerId, seed)
    }

    override fun findById(customerId: String): Customer? = customers[customerId]

    override fun save(customer: Customer): Customer {
        customers[customer.customerId] = customer
        return customer
    }
}
