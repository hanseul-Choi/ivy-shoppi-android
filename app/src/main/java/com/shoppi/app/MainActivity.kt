package com.shoppi.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

// Compat 키워드? : 이전버전 구현체와 호환성을 가진다는 의미
class MainActivity : AppCompatActivity() {
    // onCreate : Activity가 생성되고 최초 한번만 불림 (ex. layout inflate, init data)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // layout inflate

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_main)
        bottomNavigationView.itemIconTintList = null // Theme의 Primary color 값을 초기화 시킴 (icon 그라데이션을 위헤)

        // navHostFragment에 대해 접근, navHostFragment가 소유한 navController를 반환
        val navController = supportFragmentManager.findFragmentById(R.id.container_main)?.findNavController()
        navController?.let {
            // navController : NavHostFragment에서 목적지 이동을 관리하는 객체
            bottomNavigationView.setupWithNavController(it) // bottomNavigationView와 FragmentContainerView의 NavHost를 연결시킴(item의 id가 같아야함)
        }
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

    /*
    fragmentManager : Activity에 fragment를 붙였다 떼었다하며 관리하는 객체
    Host fragment layout은 Host Activity layout이 있어야 생성할 수 있음,
    Host Fragment layout은 child fragment들을 관리함

    (Host Activity)FragmentActivity -> FragmentManager
    (Host Fragment)Fragment -> parentFragmentManager(= Host Activity의 FragmentManager)
    (Host Fragment)Fragment -> childFragmentManager
    (Child Fragment)Fragment -> parentFragmentManager(= Host Fragment의 FragmentManager)
    */
}