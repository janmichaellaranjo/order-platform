package com.demo_company.payment_service.domain.model

data class PaymentTransaction(
    val transactionId: String,
    val orderId: String,
    val customerId: String,
    val amountsCents: Long,
    val currency: String
)
