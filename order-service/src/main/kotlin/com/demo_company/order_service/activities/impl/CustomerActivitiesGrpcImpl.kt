package com.demo_company.order_service.activities.impl

import com.demo_company.order_service.activities.CustomerActivities
import com.democompany.orderplatform.common.grpc.customer.CustomerServiceGrpc
import com.democompany.orderplatform.common.grpc.customer.GetCustomerRequest

class CustomerActivitiesGrpcImpl(
    private val customerServiceStub: CustomerServiceGrpc.CustomerServiceBlockingStub
) : CustomerActivities {

    override fun getShippingAddress(customerId: String): String {
        val response = customerServiceStub.getCustomer(
            GetCustomerRequest.newBuilder()
                .setCustomerId(customerId)
                .build()
        )
        val addr = response.shippingAddress
        return "${addr.street}, ${addr.city}, ${addr.state} ${addr.zip}, ${addr.country}"
    }
}
