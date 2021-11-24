package com.thanhtam.bookingtour.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thanhtam.bookingtour.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_tour.*
import kotlinx.android.synthetic.main.item_tour.view.*

class DetailActivity : AppCompatActivity() {

    //    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
//        binding = ActivityDetailBinding.inflate(layoutInflater)

        val tourData = intent
        val tourName = tourData.getStringExtra("tourName")

        val tourDescription = tourData.getStringExtra("tourDescription")
        val tourPrice = tourData.getStringExtra("tourPrice")

        val tourRating = tourData.getStringExtra("tourRating")

        txt_nameDetail.text = tourName
        txt_description.text = tourDescription

        if (tourRating != null) {
            rb_tourDetail.rating = tourRating.toFloat()
        }

        txt_tourPriceDetail.text = tourPrice

        btnBookTour.setOnClickListener {
            val intent = Intent(this@DetailActivity, CheckActivity::class.java)
            startActivity(intent)
        }
        imgBack.setOnClickListener {
            this.finish()
        }
    }

}