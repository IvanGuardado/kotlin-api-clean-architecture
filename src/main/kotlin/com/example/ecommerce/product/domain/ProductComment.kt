package com.example.ecommerce.product.domain

import com.example.ecommerce.shared.domain.user.UserId
import java.util.*

data class ProductComment(val comment: String, val userId: UserId, val createdAt: Date)
