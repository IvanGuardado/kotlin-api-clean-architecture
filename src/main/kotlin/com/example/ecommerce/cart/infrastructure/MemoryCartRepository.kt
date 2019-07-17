package com.example.ecommerce.cart.infrastructure

import com.example.ecommerce.cart.domain.Cart
import com.example.ecommerce.cart.domain.CartRepository
import com.example.ecommerce.shared.domain.user.UserId

class MemoryCartRepository : CartRepository {
    private var carts = listOf<Cart>()

    override fun findByUserId(userId: UserId): Cart? = carts.firstOrNull {
        it.userId == userId
    }


    override fun save(cart: Cart) {
        carts = carts.filter { it.id != cart.id }.plus(cart)
    }

}