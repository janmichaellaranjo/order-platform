package com.demo_company.order_service

import com.demo_company.order_service.workflows.ProcessOrderRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController(private val orderService: OrderService) {

    @PostMapping
    fun placeOrder(@RequestBody request: ProcessOrderRequest): ResponseEntity<String> {
        val workflowId = orderService.placeOrder(request)
        return ResponseEntity.accepted().body("Workflow started: $workflowId")
    }
}
