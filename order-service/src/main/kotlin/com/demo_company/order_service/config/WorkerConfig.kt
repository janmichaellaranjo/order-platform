package com.demo_company.order_service.config

import com.demo_company.order_service.activities.CustomerActivities
import com.demo_company.order_service.activities.FulfillmentActivities
import com.demo_company.order_service.activities.InventoryActivities
import com.demo_company.order_service.activities.PaymentActivitíes
import com.demo_company.order_service.workflows.OrderWorkflowImpl
import io.temporal.client.WorkflowClient
import io.temporal.worker.WorkerFactory
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration

@Configuration
class WorkerConfig(
    private val workflowClient: WorkflowClient,
    private val customerActivities: CustomerActivities,
    private val inventoryActivities: InventoryActivities,
    private val paymentActivities: PaymentActivitíes,
    private val fulfillmentActivities: FulfillmentActivities
) {

    @PostConstruct
    fun startWorker() {
        val factory = WorkerFactory.newInstance(workflowClient)
        val worker = factory.newWorker("order-processing")
        worker.registerWorkflowImplementationTypes(OrderWorkflowImpl::class.java)
        worker.registerActivitiesImplementations(
            customerActivities,
            inventoryActivities,
            paymentActivities,
            fulfillmentActivities
        )
        factory.start()
    }
}
