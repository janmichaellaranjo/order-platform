package com.demo_company.order_service.activities.impl

import com.demo_company.order_service.activities.FulfillmentActivities
import com.demo_company.order_service.activities.FulfillmentItem
import com.democompany.orderplatform.common.grpc.fulfillment.FulfillmentServiceGrpc
import com.democompany.orderplatform.common.grpc.fulfillment.ScheduleFulfillmentRequest
import com.democompany.orderplatform.common.grpc.fulfillment.FulfillmentItem as ProtoFulfillmentItem

class FulfillmentActivitiesGrpcImpl(
    private val fulfillmentServiceStub: FulfillmentServiceGrpc.FulfillmentServiceBlockingStub
) : FulfillmentActivities {

    override fun scheduleFulfillment(orderId: String, items: List<FulfillmentItem>, shippingAddress: String): String {
        val response = fulfillmentServiceStub.scheduleFulfillment(
            ScheduleFulfillmentRequest.newBuilder()
                .setOrderId(orderId)
                .addAllItems(items.map { item ->
                    ProtoFulfillmentItem.newBuilder()
                        .setProductId(item.productId)
                        .setQuantity(item.quantity)
                        .build()
                })
                .setShippingAddress(shippingAddress)
                .build()
        )
        return response.trackingReference
    }
}