package com.example.ecommerce.cart.usecase

import com.example.ecommerce.cart.domain.Cart
import com.example.ecommerce.cart.domain.CartNotFound
import com.example.ecommerce.cart.domain.CartRepository
import com.example.ecommerce.shared.domain.user.UserId

class GetCart(val cartRepository: CartRepository) {

    operator fun invoke(clientUserId: String): Cart {
        val userId = UserId(clientUserId)
        return cartRepository.findByUserId(userId) ?: throw CartNotFound()
    }
}