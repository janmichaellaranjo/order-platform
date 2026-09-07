package com.demo_company.payment_service.infrastructure.grpc

import com.demo_company.payment_service.application.command.PaymentCaptureRequest
import com.demo_company.payment_service.application.service.PaymentService
import com.democompany.orderplatform.common.grpc.payment.CapturePaymentRequest
import com.democompany.orderplatform.common.grpc.payment.CapturePaymentResponse
import com.democompany.orderplatform.common.grpc.payment.PaymentServiceGrpcKt
import com.democompany.orderplatform.common.grpc.payment.RefundPaymentRequest
import com.democompany.orderplatform.common.grpc.payment.RefundPaymentResponse
import org.springframework.grpc.server.service.GrpcService

@GrpcService
class PaymentGrpcService(
    private val paymentService: PaymentService
): PaymentServiceGrpcKt.PaymentServiceCoroutineImplBase() {

    override suspend fun capturePayment(request: CapturePaymentRequest): CapturePaymentResponse {
        val paymentTransaction = paymentService.capturePayment(PaymentCaptureRequest(
            request.orderId,
            request.customerId,
            request.amountCents,
            request.currency
        ))
        return CapturePaymentResponse.newBuilder()
            .setTransactionId(paymentTransaction.transactionId)
            .build()
    }

    override suspend fun refundPayment(request: RefundPaymentRequest): RefundPaymentResponse {
        paymentService.refundPayment(request.transactionId)
        return RefundPaymentResponse.newBuilder()
            .setSuccess(true)
            .build()
    }
}