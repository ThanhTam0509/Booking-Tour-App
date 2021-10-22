package com.thanhtam.bookingtour.ui.auth.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.thanhtam.bookingtour.MainActivity
import com.thanhtam.bookingtour.R
import com.thanhtam.bookingtour.ui.BottomActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        CoroutineScope(Dispatchers.Main).launch {
            delay(4000)
            startActivity(Intent(this@HomeActivity, BottomActivity::class.java))
        }
    }
}