package com.example.ecommerce.cart.domain

import com.example.ecommerce.shared.domain.AggregateRoot
import com.example.ecommerce.shared.domain.user.UserId
import java.time.ZonedDateTime


class Cart(val id: CartId, val userId: UserId, var products: CartProducts) : AggregateRoot() {


    fun addProduct(cartProduct: CartProduct): Cart {
        val newQuantity = products.findProductQuantity(cartProduct).increase()
        return updateProductQuantity(cartProduct, newQuantity).run {
            recordEvent(ProductAddedToCart(this.id, ZonedDateTime.now()))
            return this
        }
    }

    fun deleteProduct(cartProduct: CartProduct): Cart {
        val newQuantity = products.findProductQuantity(cartProduct).decrease()
        return updateProductQuantity(cartProduct, newQuantity)
    }

    fun updateProductQuantity(cartProduct: CartProduct, quantity: Quantity): Cart {
        val newProducts = products.updateProductQuantity(cartProduct, quantity)
        products = newProducts
        return this
    }

    companion object {
        fun create(userId: UserId): Cart {
            val cart =
                Cart(
                    CartId.generate(),
                    userId,
                    mapOf()
                )
            cart.recordEvent(CartCreatedEvent(cart.id, ZonedDateTime.now()))
            return cart
        }
    }

}

typealias CartProducts =  Map<CartProduct, Quantity>

private fun CartProducts.findProductQuantity(cartProduct: CartProduct) =
    this.getOrDefault(cartProduct, Quantity(0))

private fun CartProducts.updateProductQuantity(cartProduct: CartProduct, newQuantity: Quantity) = when(newQuantity.amount) {
    0    -> this.minus(cartProduct)
    else -> this.plus(Pair(cartProduct, newQuantity))
}
