package com.example.ecommerce.shared.infrastructure.event

import com.example.ecommerce.shared.domain.event.DomainEvent
import com.example.ecommerce.shared.domain.event.EventPublisher

class DummyEventPublisher : EventPublisher {

    override fun invoke(event: DomainEvent) {
        println(event)
    }

}