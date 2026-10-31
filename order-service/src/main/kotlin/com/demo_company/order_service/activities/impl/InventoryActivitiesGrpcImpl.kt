package com.demo_company.order_service.activities.impl

import com.demo_company.order_service.activities.InventoryActivities
import com.democompany.orderplatform.common.grpc.inventory.InventoryServiceGrpc
import com.democompany.orderplatform.common.grpc.inventory.ReleaseStockRequest
import com.democompany.orderplatform.common.grpc.inventory.ReserveStockRequest

class InventoryActivitiesGrpcImpl(
    private val inventoryServiceStub: InventoryServiceGrpc.InventoryServiceBlockingStub
) : InventoryActivities {

    override fun reserveStock(orderId: String, productId: String, quantity: Int): String {
        val response = inventoryServiceStub.reserveStock(
            ReserveStockRequest.newBuilder()
                .setOrderId(orderId)
                .setProductId(productId)
                .setQuantity(quantity)
                .build()
        )
        return response.reservationId
    }

    override fun releaseStock(orderId: String): Boolean {
        val response = inventoryServiceStub.releaseStock(
            ReleaseStockRequest.newBuilder()
                .setOrderId(orderId)
                .build()
        )
        return response.success
    }
}