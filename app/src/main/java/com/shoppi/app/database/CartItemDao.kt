package com.shoppi.app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shoppi.app.model.CartItem

// 데이터 추가, 삭제, 조회 요청
@Dao
interface CartItemDao {

    // data 추가
    // onConflict : 이미 추가되어있는 데이터 중에서 일부만 변경하기 위해 사용
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartItem)

    // table에 저장된 모든 데이터를 가져옴
    @Query("SELECT * FROM cart_item")
    suspend fun load(): List<CartItem>
}