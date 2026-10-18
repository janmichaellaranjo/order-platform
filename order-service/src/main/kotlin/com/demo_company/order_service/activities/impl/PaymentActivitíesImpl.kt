package com.demo_company.order_service.activities.impl

import com.demo_company.order_service.activities.PaymentActivitíes

class PaymentActivitíesImpl : PaymentActivitíes {

    override fun capturePayment(orderId: String, customerId: String, amountCents: Long, currency: String): String {
        TODO("Not yet implemented")
    }

    override fun refundPayment(orderId: String): Boolean {
        TODO("Not yet implemented")
    }
}