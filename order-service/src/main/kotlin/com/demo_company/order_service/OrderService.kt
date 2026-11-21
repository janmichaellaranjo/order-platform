package com.demo_company.order_service

import com.demo_company.order_service.workflows.OrderWorkflow
import com.demo_company.order_service.workflows.ProcessOrderRequest
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import org.springframework.stereotype.Service

@Service
class OrderService(private val workflowClient: WorkflowClient) {

    fun placeOrder(request: ProcessOrderRequest): String {
        val stub = workflowClient.newWorkflowStub(
            OrderWorkflow::class.java,
            WorkflowOptions.newBuilder()
                .setTaskQueue("order-processing")
                .setWorkflowId("order-${request.orderId}")
                .build()
        )
        WorkflowClient.start(stub::processOrder, request)
        return "order-${request.orderId}"
    }
}
