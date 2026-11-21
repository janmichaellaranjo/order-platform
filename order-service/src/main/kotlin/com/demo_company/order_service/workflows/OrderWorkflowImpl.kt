package com.demo_company.order_service.workflows

import com.demo_company.order_service.activities.CustomerActivities
import com.demo_company.order_service.activities.FulfillmentActivities
import com.demo_company.order_service.activities.FulfillmentItem
import com.demo_company.order_service.activities.InventoryActivities
import com.demo_company.order_service.activities.PaymentActivitíes
import io.temporal.activity.ActivityOptions
import io.temporal.common.RetryOptions
import io.temporal.workflow.Saga
import io.temporal.workflow.Workflow
import java.time.Duration

class OrderWorkflowImpl(
    val customerActivities: CustomerActivities = Workflow.newActivityStub(
        CustomerActivities::class.java,
        ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(30))
            .setRetryOptions(RetryOptions.newBuilder()
                .setMaximumAttempts(0)
                .build())
            .build()
    ),
    val paymentActivitíes: PaymentActivitíes = Workflow.newActivityStub(
        PaymentActivitíes::class.java,
        ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(30))
            .build(),
        mapOf(
            "CapturePayment" to ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(30))
                .setRetryOptions(RetryOptions.newBuilder()
                    .setMaximumAttempts(0)
                    .build())
                .build(),
            "RefundPayment" to ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(30))
                .setRetryOptions(RetryOptions.newBuilder()
                    .setMaximumAttempts(0)
                    .build())
                .build()
        )
    ),
    val inventoryActivities: InventoryActivities = Workflow.newActivityStub(
        InventoryActivities::class.java,
        ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(30))
            .build(),
        mapOf(
            "ReserveStock" to ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(30))
                .setRetryOptions(RetryOptions.newBuilder()
                    .setMaximumAttempts(0)
                    .build())
                .build(),
            "ReleaseStock" to ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(30))
                .setRetryOptions(RetryOptions.newBuilder()
                    .setMaximumAttempts(0)
                    .build())
                .build()
        )
    ),
    val fulfillmentActivities: FulfillmentActivities = Workflow.newActivityStub(
        FulfillmentActivities::class.java,
        ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(30))
            .build()
    )
) : OrderWorkflow {

    override fun processOrder(processOrderRequest: ProcessOrderRequest) {

        val shippingAddress = customerActivities.getShippingAddress(
            processOrderRequest.customerId
        )

        val saga = Saga(Saga.Options.Builder()
            .setParallelCompensation(false)
            .build())

        try {
            saga.addCompensation(
                paymentActivitíes::refundPayment,
                processOrderRequest.orderId)
            paymentActivitíes.capturePayment(
                processOrderRequest.orderId,
                processOrderRequest.customerId,
                10,
                "USD"
            )

            processOrderRequest.items.stream()
                .forEach { item ->
                    inventoryActivities.reserveStock(
                        processOrderRequest.orderId,
                        item.productId,
                        item.quantity)
                }

            saga.addCompensation(
                inventoryActivities::releaseStock,
                processOrderRequest.orderId)
            fulfillmentActivities.scheduleFulfillment(
                orderId = processOrderRequest.orderId,
                processOrderRequest.items.stream()
                    .map { item -> FulfillmentItem(item.productId, item.quantity) }
                    .toList(),
                shippingAddress)

        } catch (exception: Exception) {
            saga.compensate()
            throw exception
        }
    }
}
