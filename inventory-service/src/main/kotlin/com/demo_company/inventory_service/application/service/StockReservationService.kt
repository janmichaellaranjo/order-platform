package com.demo_company.inventory_service.application.service

import com.demo_company.inventory_service.application.command.StockReservationRequest
import com.demo_company.inventory_service.application.port.UseCases
import com.demo_company.inventory_service.domain.exception.StockReservationExceptions
import com.demo_company.inventory_service.domain.model.StockReservation
import com.demo_company.inventory_service.domain.repository.StockReservationRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class StockReservationService(
    private val stockReservationRepository: StockReservationRepository
): UseCases.ReserveStockUseCase, UseCases.ReleaseStockUseCase {

    override fun reserveStock(request: StockReservationRequest): StockReservation {
        return stockReservationRepository.create(StockReservation(
            UUID.randomUUID().toString(),
            request.orderId,
            request.productId))
    }

    override fun releaseStock(reservationId: String) {
        stockReservationRepository.findById(reservationId)
            ?: throw StockReservationExceptions.ReservationNotFoundException(reservationId)

        stockReservationRepository.delete(reservationId)
    }
}