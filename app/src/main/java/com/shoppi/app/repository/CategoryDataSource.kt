package com.shoppi.app.repository

import com.shoppi.app.model.Category

interface CategoryDataSource {

    fun getCategories() : List<Category>
}