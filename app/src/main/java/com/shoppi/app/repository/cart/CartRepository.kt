package com.shoppi.app.repository.cart

import com.shoppi.app.model.CartItem
import com.shoppi.app.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRepository(
    private val localDataSource: CartItemLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    // viewModel에서 호출(UI Thread)하기 때문에 IO 쓰레드로 전환하여 사용
    suspend fun getCartItems(): List<CartItem> {
        return withContext(ioDispatcher) {
            localDataSource.getCartItems()
        }
    }

    // 시나리오 : 상품상세화면(Product 데이터) -> 데이터 추가
    suspend fun addCartItem(product: Product) {
        withContext(ioDispatcher) {

            // Product data를 CartItem으로 변환하는 과정
            val cartItem = CartItem(
                productId = product.productId,
                label = product.label,
                price = product.price,
                brandName = product.brandName ?: "",
                thumbnailImageUrl = product.thumbnailImageUrl ?: "",
                type = product.type ?: "",
                amount = 1
            )
            localDataSource.addCartItem(cartItem)
        }
    }
}