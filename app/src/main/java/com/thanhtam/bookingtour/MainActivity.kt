package com.thanhtam.bookingtour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.thanhtam.bookingtour.data.UserPreferences
import com.thanhtam.bookingtour.ui.auth.AuthActivity
import com.thanhtam.bookingtour.ui.auth.home.HomeActivity
import com.thanhtam.bookingtour.ui.auth.startNewActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)

        userPreferences.authToken.asLiveData().observe(this, Observer {
            val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
            CoroutineScope(Dispatchers.Main).launch {
                delay(4000)
                startNewActivity(activity)
            }
        })
//        CoroutineScope(Dispatchers.Main).launch {
//            delay(4000)
//            startActivity(Intent(this@MainActivity, AuthActivity::class.java))
//        }
    }
}