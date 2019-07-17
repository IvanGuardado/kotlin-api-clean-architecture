package com.example.ecommerce.product.domain

import com.example.ecommerce.shared.domain.event.DomainEvent
import com.example.ecommerce.shared.domain.product.ProductId
import java.time.ZonedDateTime

data class ProductCreated(val productId: ProductId, val date: ZonedDateTime) : DomainEvent(productId.value, date)