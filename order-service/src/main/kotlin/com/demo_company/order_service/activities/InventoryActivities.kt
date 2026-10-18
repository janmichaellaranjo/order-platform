package com.demo_company.order_service.activities

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface InventoryActivities {

    @ActivityMethod
    fun reserveStock(orderId: String, productId: String, quantity: Int): String

    @ActivityMethod
    fun releaseStock(orderId: String): Boolean
}
