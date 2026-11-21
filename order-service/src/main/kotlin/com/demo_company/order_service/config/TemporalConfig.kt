package com.demo_company.order_service.config

import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TemporalConfig(
    @Value("\${temporal.host-port:localhost:7233}") val hostPort: String
) {

    @Bean
    fun workflowServiceStubs(): WorkflowServiceStubs =
        WorkflowServiceStubs.newLocalServiceStubs()

    @Bean
    fun workflowClient(stubs: WorkflowServiceStubs): WorkflowClient =
        WorkflowClient.newInstance(stubs)
}
