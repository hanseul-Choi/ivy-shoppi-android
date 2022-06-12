package com.shoppi.app.model

import com.google.gson.annotations.SerializedName

// Gson을 사용하기 위해 큰 틀의 Data Structure 필요
data class HomeData(
    val title: Title,
    // json 객체 키와의 매핑을 위해(다른 네이밍 사용)
    @SerializedName("top_banners") val topBanners: List<Banner>,
    val promotions: Promotion
)