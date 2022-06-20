package com.shoppi.app.repository.cart

import com.shoppi.app.model.CartItem

interface CartItemDataSource {

    // db작업은 오래 걸릴 수도 있으므로 코루틴 쓰레드에서 동작한다.
    suspend fun getCartItems(): List<CartItem>

    suspend fun addCartItem(cartItem: CartItem)
}