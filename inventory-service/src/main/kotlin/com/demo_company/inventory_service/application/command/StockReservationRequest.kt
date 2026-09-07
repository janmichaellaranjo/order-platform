package com.demo_company.inventory_service.application.command

data class StockReservationRequest(
    val orderId: String,
    val productId: String,
    val quantity: Int
)