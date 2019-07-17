package com.example.ecommerce.shared.domain.user

import com.example.ecommerce.shared.infrastructure.Uuid

data class UserId(override val value: String): Uuid(value) {

    companion object {
        fun generate() = UserId(generateRandom())
    }
}
