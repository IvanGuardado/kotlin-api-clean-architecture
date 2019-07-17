package com.example.ecommerce.cart.domain

import com.example.ecommerce.shared.infrastructure.Uuid

data class CartId(override val value: String): Uuid(value) {

    companion object {
        fun generate() = CartId(generateRandom())
    }
}
