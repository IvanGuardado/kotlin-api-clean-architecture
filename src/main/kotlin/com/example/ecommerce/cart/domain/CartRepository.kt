package com.example.ecommerce.cart.domain

import com.example.ecommerce.shared.domain.user.UserId


// This should be implemented in a more abstract way. Perhaps using the Specification Pattern
interface CartRepository {
    fun findByUserId(userId: UserId): Cart?
    fun save(cart: Cart)
}
