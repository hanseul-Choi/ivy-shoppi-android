package com.shoppi.app.ui.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppi.app.model.Product
import com.shoppi.app.repository.cart.CartRepository
import com.shoppi.app.repository.productdetail.ProductDetailRepository
import com.shoppi.app.ui.common.Event
import kotlinx.coroutines.launch

// Cart에 아이템 추가를 위해 2가의 Repository를 받음
class ProductDetailViewModel(
    private val productDetailRepository: ProductDetailRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    private val _addCartEvent = MutableLiveData<Event<Unit>>()
    val addCartEvent: LiveData<Event<Unit>> = _addCartEvent

    fun loadProductDetail(productId: String) {
        viewModelScope.launch {
            _product.value = productDetailRepository.getProductDetail(productId)
        }
    }

    fun addCart(product: Product) {
        viewModelScope.launch {
            cartRepository.addCartItem(product)
            _addCartEvent.value = Event(Unit) // event 호출
        }
    }
}