package com.demo_company.customer_service.domain.model

data class Customer(
    val customerId: String,
    val name: String,
    val shippingAddress: ShippingAddress
)

data class ShippingAddress(
    val street: String,
    val city: String,
    val state: String,
    val zip: String,
    val country: String
) {
    fun formatted(): String = "$street, $city, $state $zip, $country"
}
