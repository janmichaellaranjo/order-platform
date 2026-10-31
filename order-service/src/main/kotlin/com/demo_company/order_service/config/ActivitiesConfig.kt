package com.demo_company.order_service.config

import com.demo_company.order_service.activities.CustomerActivities
import com.demo_company.order_service.activities.FulfillmentActivities
import com.demo_company.order_service.activities.InventoryActivities
import com.demo_company.order_service.activities.PaymentActivitíes
import com.demo_company.order_service.activities.impl.CustomerActivitiesGrpcImpl
import com.demo_company.order_service.activities.impl.FulfillmentActivitiesGrpcImpl
import com.demo_company.order_service.activities.impl.InventoryActivitiesGrpcImpl
import com.demo_company.order_service.activities.impl.PaymentActivitíesImpl
import com.democompany.orderplatform.common.grpc.customer.CustomerServiceGrpc
import com.democompany.orderplatform.common.grpc.fulfillment.FulfillmentServiceGrpc
import com.democompany.orderplatform.common.grpc.inventory.InventoryServiceGrpc
import com.democompany.orderplatform.common.grpc.payment.PaymentServiceGrpc
import io.grpc.ManagedChannel
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.grpc.client.GrpcChannelFactory

@Configuration
class ActivitiesConfig(private val channelFactory: GrpcChannelFactory) {

    @Bean
    fun customerActivities(): CustomerActivities {
        val channel: ManagedChannel = channelFactory.createChannel("customer")
        return CustomerActivitiesGrpcImpl(CustomerServiceGrpc.newBlockingStub(channel))
    }

    @Bean
    fun inventoryActivities(): InventoryActivities {
        val channel: ManagedChannel = channelFactory.createChannel("inventory")
        return InventoryActivitiesGrpcImpl(InventoryServiceGrpc.newBlockingStub(channel))
    }

    @Bean
    fun paymentActivities(): PaymentActivitíes {
        val channel: ManagedChannel = channelFactory.createChannel("payment")
        return PaymentActivitíesImpl(PaymentServiceGrpc.newBlockingStub(channel))
    }

    @Bean
    fun fulfillmentActivities(): FulfillmentActivities {
        val channel: ManagedChannel = channelFactory.createChannel("fulfillment")
        return FulfillmentActivitiesGrpcImpl(FulfillmentServiceGrpc.newBlockingStub(channel))
    }
}
