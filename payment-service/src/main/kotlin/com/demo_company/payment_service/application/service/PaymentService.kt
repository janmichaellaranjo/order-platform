package com.demo_company.payment_service.application.service

import com.demo_company.payment_service.application.command.PaymentCaptureRequest
import com.demo_company.payment_service.application.port.UseCases
import com.demo_company.payment_service.domain.PaymentTransactionNotFoundExceptions
import com.demo_company.payment_service.domain.model.PaymentTransaction
import com.demo_company.payment_service.domain.repository.TransactionRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PaymentService(
    private val transactionRepository: TransactionRepository
): UseCases.CapturePaymentUseCase, UseCases.RefundPaymentUseCase {

    override fun capturePayment(paymentCaptureRequest: PaymentCaptureRequest): PaymentTransaction {
        val transaction = transactionRepository.create(PaymentTransaction(
            UUID.randomUUID().toString(),
            paymentCaptureRequest.orderId,
            paymentCaptureRequest.customerId,
            paymentCaptureRequest.amountsCents,
            paymentCaptureRequest.currency
        ))

        return transaction
    }

    override fun refundPayment(transactionId: String) {
        transactionRepository.findById(transactionId)
            ?: throw PaymentTransactionNotFoundExceptions.TransactionNotFoundException(transactionId)

        transactionRepository.delete(transactionId)
    }
}