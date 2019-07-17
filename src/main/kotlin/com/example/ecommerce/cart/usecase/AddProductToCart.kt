package com.example.ecommerce.cart.usecase

import com.example.ecommerce.cart.domain.Cart
import com.example.ecommerce.cart.domain.CartProduct
import com.example.ecommerce.cart.domain.CartRepository
import com.example.ecommerce.cart.domain.ProductNotFound
import com.example.ecommerce.product.domain.ProductRepository
import com.example.ecommerce.shared.domain.event.EventPublisher
import com.example.ecommerce.shared.domain.product.ProductId
import com.example.ecommerce.shared.domain.user.UserId

class AddProductToCart(
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository,
    private val eventPublisher: EventPublisher
) {

    operator fun invoke(clientUserId: String, clientProductId: String) {
        val userId = UserId(clientUserId)
        val productId = ProductId(clientProductId)
        val cart = cartRepository.findByUserId(userId)?: Cart.create(userId)
        val product = productRepository.findById(productId)?: throw ProductNotFound()

        cart
            .addProduct(CartProduct(product.id, product.name, product.price))
            .save()
            .publishEvents()

    }

    private fun Cart.save(): Cart {
        cartRepository.save(this)
        return this
    }

    private fun Cart.publishEvents(): Cart {
        eventPublisher(this.pullEvents())
        return this
    }
}