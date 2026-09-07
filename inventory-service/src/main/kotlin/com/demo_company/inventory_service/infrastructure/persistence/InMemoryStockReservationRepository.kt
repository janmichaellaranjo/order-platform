package com.demo_company.inventory_service.infrastructure.persistence

import com.demo_company.inventory_service.domain.model.StockReservation
import com.demo_company.inventory_service.domain.repository.StockReservationRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class InMemoryStockReservationRepository(): StockReservationRepository {
    private val stockReservations = ConcurrentHashMap<String, StockReservation>()

    override fun create(stockReservation: StockReservation): StockReservation {
        stockReservations[stockReservation.reservationId] = stockReservation

        return stockReservation
    }

    override fun findById(reservationId: String): StockReservation? {
        return stockReservations[reservationId]
    }

    override fun delete(reservationId: String) {
        stockReservations.remove(reservationId)
    }
}