package com.shoppi.app.repository.home

import com.shoppi.app.model.HomeData

// DataSource는 파일, 데이터베이스, 네트워크 등 다양한 소스로부터 요청하기 때문에 인터페이스를 통해 구현하여 사용
interface HomeDataSource {

    fun getHomeData(): HomeData?
}