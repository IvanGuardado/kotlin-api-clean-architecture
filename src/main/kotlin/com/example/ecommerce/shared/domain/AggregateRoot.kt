package com.example.ecommerce.shared.domain

import com.example.ecommerce.shared.domain.event.DomainEvent

abstract class AggregateRoot {

    protected var domainEvents = ArrayList<DomainEvent>()

    fun pullEvents(): ArrayList<DomainEvent> {
        val lastEvents = domainEvents.toList()
        domainEvents.clear()
        return ArrayList(lastEvents)

    }

    protected fun recordEvent(event: DomainEvent) {
        domainEvents.add(event)
    }

}