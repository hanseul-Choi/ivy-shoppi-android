package com.shoppi.app.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shoppi.app.AssetLoader
import com.shoppi.app.repository.HomeAssetDataSource
import com.shoppi.app.repository.HomeRepository
import com.shoppi.app.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) { // 만약, viewModel이 HomeViewModel로 들어왔다면,
            val respository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
            return HomeViewModel(respository) as T
        } else {
            throw IllegalArgumentException("Failed to create ViewModel : ${modelClass.name}")
        }
    }
}