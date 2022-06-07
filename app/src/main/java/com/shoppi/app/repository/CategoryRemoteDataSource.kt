package com.shoppi.app.repository

import com.shoppi.app.model.Category
import com.shoppi.app.ui.network.ApiClient

class CategoryRemoteDataSource(private val apiClient: ApiClient) : CategoryDataSource {
    override fun getCategories(): List<Category> {
        return apiClient.getCategories()
    }
}