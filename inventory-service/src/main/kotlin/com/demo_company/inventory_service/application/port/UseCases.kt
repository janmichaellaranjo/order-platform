package com.demo_company.inventory_service.application.port

import com.demo_company.inventory_service.application.command.StockReservationRequest
import com.demo_company.inventory_service.domain.model.StockReservation

interface UseCases {

    interface ReserveStockUseCase {
        fun reserveStock(request: StockReservationRequest): StockReservation
    }

    interface ReleaseStockUseCase {
        fun releaseStock(reservationId: String)
    }
}