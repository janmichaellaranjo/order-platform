package com.demo_company.order_service.activities

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface PaymentActivitíes {

    @ActivityMethod
    fun capturePayment(orderId: String, customerId: String, amountCents: Long, currency: String): String

    @ActivityMethod
    fun refundPayment(orderId: String): Boolean
}