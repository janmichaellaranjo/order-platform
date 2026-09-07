package com.demo_company.inventory_service.infrastructure.grpc

import com.demo_company.inventory_service.application.command.StockReservationRequest
import com.demo_company.inventory_service.application.service.StockReservationService
import com.democompany.orderplatform.common.grpc.inventory.InventoryServiceGrpcKt
import com.democompany.orderplatform.common.grpc.inventory.ReleaseStockRequest
import com.democompany.orderplatform.common.grpc.inventory.ReleaseStockResponse
import com.democompany.orderplatform.common.grpc.inventory.ReserveStockRequest
import com.democompany.orderplatform.common.grpc.inventory.ReserveStockResponse
import org.springframework.grpc.server.service.GrpcService

@GrpcService
class InventoryGrpcService(
    private val stockReservationService: StockReservationService
) : InventoryServiceGrpcKt.InventoryServiceCoroutineImplBase() {

    override suspend fun reserveStock(request: ReserveStockRequest): ReserveStockResponse {
        val stockReservation = stockReservationService.reserveStock(
            StockReservationRequest(
                request.orderId,
                request.productId,
                request.quantity
            )
        )

        return ReserveStockResponse.newBuilder()
            .setReservationId(stockReservation.reservationId)
            .build()
    }

    override suspend fun releaseStock(request: ReleaseStockRequest): ReleaseStockResponse {
        stockReservationService.releaseStock(request.reservationId)

        return ReleaseStockResponse.newBuilder()
            .setSuccess(true)
            .build()

    }
}