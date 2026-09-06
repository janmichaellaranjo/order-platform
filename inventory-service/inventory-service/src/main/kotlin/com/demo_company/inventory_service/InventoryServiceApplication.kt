package com.demo_company.inventory_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InventoryServiceApplication

fun main(args: Array<String>) {
	runApplication<InventoryServiceApplication>(*args)
}
