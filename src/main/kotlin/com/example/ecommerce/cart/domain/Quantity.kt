package com.example.ecommerce.cart.domain

data class Quantity(val amount: Int) {

    init {
        if (amount < 0) throw InvalidQuantity(amount)
    }

    fun increase() = copy(amount = amount + 1)
    fun decrease() = copy(amount = amount - 1)
}