package com.example.ecommerce.cart.domain

import com.example.ecommerce.shared.domain.product.ProductId
import com.example.ecommerce.shared.domain.product.ProductName
import com.example.ecommerce.shared.domain.Price

data class CartProduct(val id: ProductId, val productName: ProductName, val price: Price)