package com.demo_company.order_service.activities.impl

import com.demo_company.order_service.activities.InventoryActivities

class InventoryActivitiesGrpcImpl : InventoryActivities {

    override fun reserveStock(orderId: String, productId: String, quantity: Int): String {
        TODO("Not yet implemented")
    }

    override fun releaseStock(reservationId: String): Boolean {
        TODO("Not yet implemented")
    }
}