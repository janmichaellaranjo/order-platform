package com.demo_company.fulfillment_service.application.command

data class FulfillmentScheduleRequest(
    val orderId: String,
    val fulfillmentDetails: List<FulfillmentDetail>,
    val shippingAddress: String)

data class FulfillmentDetail(
    val productId: String,
    val quantity: Int)
