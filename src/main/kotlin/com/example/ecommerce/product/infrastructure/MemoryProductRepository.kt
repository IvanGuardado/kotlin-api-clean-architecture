package com.example.ecommerce.product.infrastructure

import com.example.ecommerce.product.domain.Product
import com.example.ecommerce.product.domain.ProductRepository
import com.example.ecommerce.shared.domain.product.ProductId

class MemoryProductRepository : ProductRepository {
    private var products = listOf<Product>()

    override fun findById(id: ProductId): Product? = products.firstOrNull {
        it.id == id
    }

    override fun save(product: Product) {
        products = products.plus(product)
    }

}