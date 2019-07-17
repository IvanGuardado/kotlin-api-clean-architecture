package com.example.ecommerce.product.usecase

import com.example.ecommerce.product.domain.*
import com.example.ecommerce.shared.domain.Price
import com.example.ecommerce.shared.domain.event.EventPublisher
import com.example.ecommerce.shared.domain.product.ProductId
import com.example.ecommerce.shared.domain.product.ProductName

class CreateProduct(val productRepository: ProductRepository, val eventPublisher: EventPublisher) {

    operator fun invoke(
        productId: ProductId,
        productSKU: ProductSKU,
        productCategory: ProductCategory,
        productName: ProductName,
        productDescription: ProductDescription,
        price: Price
    ) {
        val product = Product.create(productId, productSKU, productCategory, productName, productDescription, price)
        productRepository.save(product)
        eventPublisher(product.pullEvents())
    }
}