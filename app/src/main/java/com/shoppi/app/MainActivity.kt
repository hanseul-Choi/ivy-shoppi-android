package com.shoppi.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

private const val TAG = "MainActivity"

// Compat 키워드? : 이전버전 구현체와 호환성을 가진다는 의미
class MainActivity : AppCompatActivity() {
    // onCreate : Activity가 생성되고 최초 한번만 불림 (ex. layout inflate, init data)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail) // layout inflate
    }

    // onStart : 액티비티가 실행될 때, 불림 (ex. start animation, refresh data)
    override fun onStart() {
        super.onStart()
    }

    // onResume : Activity가 사용자 화면에 보일 때 = focus를 얻을 때 불림 (자주 호출)
    override fun onResume() {
        super.onResume()
    }

    // onPause : Activity가 focus를 잃었을 때 불림 (자주 호출)
    override fun onPause() {
        super.onPause()
    }

    // onStop : Activity가 잠깐 종료될 때 불림 (home키를 통해 다른 앱을 사용할 때), (ex. stop refresh data, stop animation)
    override fun onStop() {
        super.onStop()
    }

    // onDestroy : 사용중인 앱 목록에서 완전히 제거될 때 불림(onStop에서 미처하지 못한 동작 처리, data 리소스 해제)
    override fun onDestroy() {
        super.onDestroy()
    }
}