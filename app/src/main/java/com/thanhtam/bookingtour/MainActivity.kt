package com.thanhtam.bookingtour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.thanhtam.bookingtour.data.UserPreferences
import com.thanhtam.bookingtour.ui.auth.AuthActivity
import com.thanhtam.bookingtour.ui.auth.home.HomeActivity
import com.thanhtam.bookingtour.ui.auth.startNewActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val userPreferences = UserPreferences(this)

//        phần animation xe chạy
        userPreferences.authToken.asLiveData().observe(this, Observer {
            val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
            CoroutineScope(Dispatchers.Main).launch {
                delay(2000)
                startNewActivity(activity)
            }
        })
    }
}