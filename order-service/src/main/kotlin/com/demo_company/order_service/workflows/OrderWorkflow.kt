package com.demo_company.order_service.workflows

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface OrderWorkflow {

    @WorkflowMethod
    fun processOrder(orderId: String)
}