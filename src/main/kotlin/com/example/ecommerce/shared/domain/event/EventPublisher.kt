package com.example.ecommerce.shared.domain.event

interface EventPublisher {

    operator fun invoke(event: DomainEvent)
    operator fun invoke(events: ArrayList<DomainEvent>) {
        events.forEach {
            invoke(it)
        }
    }

}
