package com.example.ecommerce.shared.domain.event

import java.time.ZonedDateTime

open class DomainEvent(aggregateId: String, occurredAt: ZonedDateTime) {
    val aggregateId = aggregateId
    val occurredAt = occurredAt
}
