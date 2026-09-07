package com.demo_company.payment_service.application.command

data class PaymentCaptureRequest(
    val orderId: String,
    val customerId: String,
    val amountsCents: Long,
    val currency: String
)