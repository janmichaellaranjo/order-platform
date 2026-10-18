package com.demo_company.order_service.activities.impl

import com.demo_company.order_service.activities.FulfillmentItem
import com.demo_company.order_service.activities.FulfillmentActivities

class FulfillmentActivitiesGrpcImpl : FulfillmentActivities {

    override fun scheduleFulfillment(orderId: String, items: List<FulfillmentItem>, shippingAddress: String): String {
        TODO("Not yet implemented")
    }
}