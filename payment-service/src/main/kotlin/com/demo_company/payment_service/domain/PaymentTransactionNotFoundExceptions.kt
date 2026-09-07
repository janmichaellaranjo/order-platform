package com.demo_company.payment_service.domain

sealed class PaymentTransactionNotFoundExceptions(message: String): RuntimeException(message) {

    class TransactionNotFoundException(id: String):
        PaymentTransactionNotFoundExceptions("The transaction $id not found") {}
}