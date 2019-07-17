package com.example.ecommerce.shared.domain.product

import com.example.ecommerce.shared.infrastructure.Uuid

data class ProductId(override val value: String) : Uuid(value) {

    companion object {
        fun generate(): ProductId = ProductId(generateRandom())
    }

}
