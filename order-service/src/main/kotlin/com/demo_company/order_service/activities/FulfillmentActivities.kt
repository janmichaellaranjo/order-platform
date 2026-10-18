package com.demo_company.order_service.activities

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface FulfillmentActivities {

    @ActivityMethod
    fun scheduleFulfillment(orderId: String, items: List<FulfillmentItem>, shippingAddress: String): String
}

data class FulfillmentItem(val productId: String, val quantity: Int)