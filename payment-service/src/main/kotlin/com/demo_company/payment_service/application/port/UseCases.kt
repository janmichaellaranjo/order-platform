package com.demo_company.payment_service.application.port

import com.demo_company.payment_service.application.command.PaymentCaptureRequest
import com.demo_company.payment_service.domain.model.PaymentTransaction


interface UseCases {

    interface CapturePaymentUseCase {
        fun capturePayment(paymentCaptureRequest: PaymentCaptureRequest): PaymentTransaction
    }

    interface RefundPaymentUseCase {
        fun refundPayment(transactionId: String)
    }
}