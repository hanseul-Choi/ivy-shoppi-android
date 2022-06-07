package com.shoppi.app.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shoppi.app.AssetLoader
import com.shoppi.app.repository.category.CategoryRemoteDataSource
import com.shoppi.app.repository.category.CategoryRepository
import com.shoppi.app.repository.home.HomeAssetDataSource
import com.shoppi.app.repository.home.HomeRepository
import com.shoppi.app.ui.category.CategoryViewModel
import com.shoppi.app.ui.home.HomeViewModel
import com.shoppi.app.ui.network.ApiClient
import java.lang.IllegalArgumentException

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> { // 만약, viewModel이 HomeViewModel로 들어왔다면,
                val repository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> { // 만약, viewModel이 CategoryViewModel로 들어왔다면,
                val repository = CategoryRepository(CategoryRemoteDataSource(ApiClient.create()))
                CategoryViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel : ${modelClass.name}")
            }
        }
    }
}