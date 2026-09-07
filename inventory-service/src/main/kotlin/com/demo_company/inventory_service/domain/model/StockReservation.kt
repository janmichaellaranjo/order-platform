package com.demo_company.inventory_service.domain.model

data class StockReservation(
    val reservationId: String,
    val orderId: String,
    val productId: String
)
