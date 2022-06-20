package com.shoppi.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// class의 계층을 정의
sealed class CartProduct
data class CartHeader(
    val brandName: String,
): CartProduct()

// room에서 사용하기 위해 Entity annotation추가

@Entity(
    tableName = "cart_item"
)
data class CartItem(
    @PrimaryKey @ColumnInfo(name = "product_id")val productId: String,
    val label: String,
    val price: Int,
    @ColumnInfo(name = "brand_name") val brandName: String,
    @ColumnInfo(name = "thumbnail_image_url") val thumbnailImageUrl: String,
    val type: String,
    val amount: Int
): CartProduct()