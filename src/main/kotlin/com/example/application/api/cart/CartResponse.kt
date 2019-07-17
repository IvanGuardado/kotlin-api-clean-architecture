package com.example.application.api.cart

import com.example.ecommerce.cart.domain.Cart
import com.example.ecommerce.cart.domain.CartProducts

data class CartResponse(
    val id: String,
    val products: List<CartProductResponse>) {

    companion object {
        fun from(cart: Cart): CartResponse {
            return CartResponse(cart.id.value, cart.products.toResponse())
        }
    }
}

data class CartProductResponse(val productId: String, val productName: String, val quantity: Number)

private fun CartProducts.toResponse(): List<CartProductResponse> {
    return this.map {
        val product = it.key
        val quantity = it.value
        CartProductResponse(product.id.value, product.productName.name, quantity.amount)
    }
}

