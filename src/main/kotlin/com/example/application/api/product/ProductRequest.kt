package com.example.application.api.product

import com.example.ecommerce.shared.domain.Currency

data class CreateProductRequest(
    val sku: String?,
    val category: String?,
    val name: String?,
    val description: String?,
    val price: PriceRequest?
)

data class PriceRequest(val amount: Int, val currency: Currency)
