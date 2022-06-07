package com.shoppi.app.repository.category

import com.shoppi.app.model.Category

class CategoryRepository(
    private val remoteDataSource: CategoryRemoteDataSource
) {

    suspend fun getCategories() : List<Category> {
        // 사실 retrofit에서 자체적으로 쓰레드를 바꿔주는 작업을 진행해준다.
//        // coroutine thread를 바꿔줄 수 있다. => 워커스레드로 변경
//        withContext(Dispatchers.IO) {
//            remoteDataSource.getCategories()
//        }
        return remoteDataSource.getCategories()
    }
}