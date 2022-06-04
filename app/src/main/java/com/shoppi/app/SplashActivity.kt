package com.shoppi.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// Android version 31부터는 Splash Screen기능을 따로 지원해준다.
// shape drawable의 bitmap tag는 png, jpg, gif만 지원한다.
class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}