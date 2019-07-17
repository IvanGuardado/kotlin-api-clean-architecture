package com.example.ecommerce.cart.domain

import com.example.ecommerce.shared.domain.event.DomainEvent
import java.time.ZonedDateTime

data class CartCreatedEvent(val cartId: CartId, val date: ZonedDateTime) : DomainEvent(cartId.value, date)
