package com.demo_company.customer_service.infrastructure.grpc

import com.demo_company.customer_service.application.command.GetCustomerRequest
import com.demo_company.customer_service.application.service.CustomerService
import com.demo_company.customer_service.domain.exception.CustomerExceptions
import com.democompany.orderplatform.common.grpc.customer.CustomerServiceGrpcKt
import com.democompany.orderplatform.common.grpc.customer.GetCustomerRequest as ProtoGetCustomerRequest
import com.democompany.orderplatform.common.grpc.customer.GetCustomerResponse
import com.democompany.orderplatform.common.grpc.customer.ShippingAddress as ProtoShippingAddress
import io.grpc.Status
import io.grpc.StatusRuntimeException
import org.springframework.grpc.server.service.GrpcService

@GrpcService
class CustomerGrpcService(
    private val customerService: CustomerService
) : CustomerServiceGrpcKt.CustomerServiceCoroutineImplBase() {

    override suspend fun getCustomer(request: ProtoGetCustomerRequest): GetCustomerResponse {
        val customer = try {
            customerService.getCustomer(GetCustomerRequest(request.customerId))
        } catch (e: CustomerExceptions.CustomerNotFoundException) {
            throw StatusRuntimeException(Status.NOT_FOUND.withDescription(e.message))
        }

        val protoAddress = ProtoShippingAddress.newBuilder()
            .setStreet(customer.shippingAddress.street)
            .setCity(customer.shippingAddress.city)
            .setState(customer.shippingAddress.state)
            .setZip(customer.shippingAddress.zip)
            .setCountry(customer.shippingAddress.country)
            .build()

        return GetCustomerResponse.newBuilder()
            .setCustomerId(customer.customerId)
            .setName(customer.name)
            .setShippingAddress(protoAddress)
            .build()
    }
}
