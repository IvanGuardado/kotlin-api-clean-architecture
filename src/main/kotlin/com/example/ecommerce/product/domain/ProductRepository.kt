package com.example.ecommerce.product.domain

import com.example.ecommerce.shared.domain.product.ProductId

interface ProductRepository {

    fun findById(id: ProductId): Product?
    fun save(product: Product)
}
