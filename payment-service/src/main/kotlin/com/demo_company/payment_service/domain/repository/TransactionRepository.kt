package com.demo_company.payment_service.domain.repository

import com.demo_company.payment_service.domain.model.PaymentTransaction

interface TransactionRepository {
    fun create(paymentTransaction: PaymentTransaction): PaymentTransaction
    fun findById(transactionId: String): PaymentTransaction?
    fun delete(transactionId: String)
}