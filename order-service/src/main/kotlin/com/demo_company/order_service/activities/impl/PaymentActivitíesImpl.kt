package com.demo_company.order_service.activities.impl

import com.demo_company.order_service.activities.PaymentActivitíes
import com.democompany.orderplatform.common.grpc.payment.CapturePaymentRequest
import com.democompany.orderplatform.common.grpc.payment.PaymentServiceGrpc
import com.democompany.orderplatform.common.grpc.payment.RefundPaymentRequest

class PaymentActivitíesImpl(
    private val paymentServiceStub: PaymentServiceGrpc.PaymentServiceBlockingStub
) : PaymentActivitíes {

    override fun capturePayment(orderId: String, customerId: String, amountCents: Long, currency: String): String {
        val response = paymentServiceStub.capturePayment(
            CapturePaymentRequest.newBuilder()
                .setOrderId(orderId)
                .setCustomerId(customerId)
                .setAmountCents(amountCents)
                .setCurrency(currency)
                .build()
        )
        return response.transactionId
    }

    override fun refundPayment(orderId: String): Boolean {
        val response = paymentServiceStub.refundPayment(
            RefundPaymentRequest.newBuilder()
                .setOrderId(orderId)
                .build()
        )
        return response.success
    }
}