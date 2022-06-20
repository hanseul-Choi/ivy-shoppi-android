package com.shoppi.app

import android.content.Context
import androidx.room.Room
import com.shoppi.app.database.AppDatabase
import com.shoppi.app.repository.cart.CartItemLocalDataSource
import com.shoppi.app.repository.cart.CartRepository
import com.shoppi.app.ui.network.ApiClient

object ServiceLocator {
    private var apiClient: ApiClient? = null
    private var database: AppDatabase? = null
    private var cartRepository: CartRepository? = null

    fun provideApiClient(): ApiClient {
        return apiClient ?: kotlin.run {
            ApiClient.create().also {
                apiClient = it
            }
        }
    }

    // db instance 생성
    private fun provideDatabase(applicationContext: Context): AppDatabase {
        return database ?: kotlin.run {
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "shoppi-local"
            ).build().also {
                database = it
            }
        }
    }

    // CartRepository가 2군데 이상에서 사용되기 때문에
    fun provideCartRepository(context: Context): CartRepository {
        return cartRepository ?: kotlin.run {
            val dao = provideDatabase(context.applicationContext).cartItemDao()
            CartRepository(CartItemLocalDataSource(dao)).also {
                cartRepository = it
            }
        }
    }
}