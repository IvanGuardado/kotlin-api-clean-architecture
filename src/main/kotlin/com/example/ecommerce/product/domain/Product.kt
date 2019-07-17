package com.example.ecommerce.product.domain

import com.example.ecommerce.shared.domain.AggregateRoot
import com.example.ecommerce.shared.domain.Price
import com.example.ecommerce.shared.domain.product.ProductId
import com.example.ecommerce.shared.domain.product.ProductName
import java.time.ZonedDateTime

class Product(
    val id: ProductId,
    val sku: ProductSKU,
    val category: ProductCategory,
    val name: ProductName,
    val description: ProductDescription,
    val highlightedUserComments: ProductComments,
    val price: Price
) : AggregateRoot() {

    companion object {
        fun create(
            id: ProductId,
            sku: ProductSKU,
            category: ProductCategory,
            name: ProductName,
            description: ProductDescription,
            price: Price
        ): Product {
            val product = Product(id, sku, category, name, description, arrayListOf(), price)
            product.recordEvent(ProductCreated(id, ZonedDateTime.now()))
            return product
        }
    }
}