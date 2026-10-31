package com.demo_company.order_service.activities

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface CustomerActivities {

    @ActivityMethod
    fun getShippingAddress(customerId: String): String
}
