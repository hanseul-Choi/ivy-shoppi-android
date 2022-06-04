package com.shoppi.app

import android.content.Context
import android.util.Log

class AssetLoader {

    // exception 처리를 위한 메소드
    fun getJsonString(context: Context, fileName: String): String? {
        // 성공과 실패를 분류하여 처리
        return kotlin.runCatching {
            loadAsset(context, fileName)
        }.getOrNull() // 실패시 null 반환, 성공시 data 반환
    }

    private fun loadAsset(context: Context, fileName: String): String {
        // asset 데이터를 얻기 위함, use : 확장함수이며 자동으로 inputStream을 닫아줌
        return context.assets.open(fileName).use { inputStream ->
            val size = inputStream.available() // inpustream이 존재하면 size가 존재
            val bytes = ByteArray(size) // inputStream에게 전달받는 data는 ByteArray type
            inputStream.read(bytes) // bytes에 복사
            String(bytes) // bytes를 string으로 변경
        }
    }
}