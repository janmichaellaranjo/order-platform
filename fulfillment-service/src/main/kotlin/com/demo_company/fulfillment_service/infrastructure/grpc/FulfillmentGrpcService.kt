package com.demo_company.fulfillment_service.infrastructure.grpc

import com.demo_company.fulfillment_service.application.command.FulfillmentDetail
import com.demo_company.fulfillment_service.application.command.FulfillmentScheduleRequest
import com.demo_company.fulfillment_service.application.service.FulfillmentService
import com.democompany.orderplatform.common.grpc.fulfillment.FulfillmentServiceGrpcKt
import com.democompany.orderplatform.common.grpc.fulfillment.ScheduleFulfillmentRequest
import com.democompany.orderplatform.common.grpc.fulfillment.ScheduleFulfillmentResponse
import org.springframework.grpc.server.service.GrpcService

@GrpcService
class FulfillmentGrpcService(
    val fulfillmentService: FulfillmentService
) : FulfillmentServiceGrpcKt.FulfillmentServiceCoroutineImplBase() {

    override suspend fun scheduleFulfillment(request: ScheduleFulfillmentRequest): ScheduleFulfillmentResponse {
        val trackingReference = fulfillmentService.scheduleFulfillment(
            FulfillmentScheduleRequest(
                request.orderId,
                request.itemsList
                    .stream()
                    .map{ item -> FulfillmentDetail(
                        item.productId,
                        item.quantity
                    ) }
                    .toList(),
                request.shippingAddress
                )
        )
        return ScheduleFulfillmentResponse.newBuilder()
            .setTrackingReference(trackingReference.id)
            .build()
    }
}