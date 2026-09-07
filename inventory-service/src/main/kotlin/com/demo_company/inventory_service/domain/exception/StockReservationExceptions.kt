package com.demo_company.inventory_service.domain.exception

import java.lang.RuntimeException

sealed class StockReservationExceptions(message: String): RuntimeException(message) {

    class ReservationNotFoundException(id: String) :
        StockReservationExceptions("Reservation $id not found")
}