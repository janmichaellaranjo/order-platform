package com.demo_company.fulfillment_service.application.port

import com.demo_company.fulfillment_service.application.command.FulfillmentScheduleRequest
import com.demo_company.fulfillment_service.domain.TrackingReference

interface UseCases {

    interface ScheduleFulfillmentUseCase {
        fun scheduleFulfillment(fulfillmentScheduleRequest: FulfillmentScheduleRequest): TrackingReference
    }
}