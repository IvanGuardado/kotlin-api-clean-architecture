package com.example.application.api

import com.example.application.api.cart.AddProductToCartRequest
import com.example.application.api.cart.CartResponse
import com.example.application.api.product.CreateProductRequest
import com.example.ecommerce.cart.infrastructure.MemoryCartRepository
import com.example.ecommerce.cart.usecase.AddProductToCart
import com.example.ecommerce.cart.usecase.GetCart
import com.example.ecommerce.product.domain.ProductCategory
import com.example.ecommerce.product.domain.ProductDescription
import com.example.ecommerce.product.domain.ProductSKU
import com.example.ecommerce.product.infrastructure.MemoryProductRepository
import com.example.ecommerce.product.usecase.CreateProduct
import com.example.ecommerce.shared.domain.Currency
import com.example.ecommerce.shared.domain.Price
import com.example.ecommerce.shared.domain.product.ProductId
import com.example.ecommerce.shared.domain.product.ProductName
import com.example.ecommerce.shared.infrastructure.event.DummyEventPublisher
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

class Server {

    private val cartRepository = MemoryCartRepository()
    private val productRepository = MemoryProductRepository()
    private val eventPublisher = DummyEventPublisher()

    private val getCart = GetCart(cartRepository)
    private val addProductToCart = AddProductToCart(cartRepository, productRepository, eventPublisher)
    private val createProduct = CreateProduct(productRepository, eventPublisher)

    fun start() {
        embeddedServer(Netty, 8080) {
            install(ContentNegotiation) {
                gson { }
            }

            routing {
                route("cart") {

                    get("/") {
                        val userId = call.request.queryParameters["userId"] ?: ""
                        call.respond(CartResponse.from(getCart(userId)))
                    }

                    post("/") {
                        val request = call.receive<AddProductToCartRequest>()
                        call.respond(addProductToCart(request.userId, request.productId))
                    }
                }

                route("product") {

                    post("/") {
                        val request = call.receive<CreateProductRequest>()
                        call.respond(
                            createProduct(
                                ProductId.generate(),
                                ProductSKU(request.sku ?: ""),
                                ProductCategory(request.category ?: ""),
                                ProductName(request.name ?: ""),
                                ProductDescription(request.description ?: ""),
                                Price(request.price?.amount ?: 0, request.price?.currency ?: Currency.EUR)
                            )
                        )
                    }
                }
            }
        }.start(wait = true)
    }
}


