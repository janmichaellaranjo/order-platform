package com.demo_company.order_service.workflows

data class ProcessOrderRequest(
    val orderId: String,
    val items: List<Item>,
    val customerId: String)

data class Item(
    val productId: String,
    val quantity: Int
)