package com.demo_company.payment_service.infrastructure.persistence

import com.demo_company.payment_service.domain.model.PaymentTransaction
import com.demo_company.payment_service.domain.repository.TransactionRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class InMemoryTransactionRepository: TransactionRepository {

    private val transactions = ConcurrentHashMap<String, PaymentTransaction>()

    override fun create(paymentTransaction: PaymentTransaction): PaymentTransaction {
        transactions[paymentTransaction.transactionId] = paymentTransaction

        return paymentTransaction
    }

    override fun findById(transactionId: String): PaymentTransaction? {
        return transactions[transactionId]
    }

    override fun delete(transactionId: String) {
        transactions.remove(transactionId)
    }
}