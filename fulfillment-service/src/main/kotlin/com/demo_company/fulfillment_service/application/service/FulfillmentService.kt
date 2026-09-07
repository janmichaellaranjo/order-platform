package com.demo_company.fulfillment_service.application.service

import com.demo_company.fulfillment_service.application.command.FulfillmentScheduleRequest
import com.demo_company.fulfillment_service.application.port.UseCases
import com.demo_company.fulfillment_service.domain.TrackingReference
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class FulfillmentService() : UseCases.ScheduleFulfillmentUseCase {
    override fun scheduleFulfillment(fulfillmentScheduleRequest: FulfillmentScheduleRequest): TrackingReference {
        return TrackingReference(UUID.randomUUID().toString())
    }
}