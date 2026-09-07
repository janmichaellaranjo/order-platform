package com.demo_company.inventory_service.domain.repository

import com.demo_company.inventory_service.domain.model.StockReservation

interface StockReservationRepository {
    fun create(stockReservation: StockReservation): StockReservation
    fun findById(reservationId: String): StockReservation?
    fun delete(reservationId: String)
}