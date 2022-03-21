package com.zeroheat.lolandroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
    getFCMDeviceToken()
    }

    fun getFCMDeviceToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            Log.d("토큰값", it.result!!) }
    }
}