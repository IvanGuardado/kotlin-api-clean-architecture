package com.example.ecommerce.shared.infrastructure

import java.util.UUID

open class Uuid(open val value: String) {

    companion object {
        fun generateRandom(): String = UUID.randomUUID().toString()
    }
}