package com.thanhtam.bookingtour.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import com.hendraanggrian.pikasso.picasso
import com.thanhtam.bookingtour.R
import com.thanhtam.bookingtour.databinding.ActivityDetailBinding
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
//        val topTourName = tourData.getStringExtra("topTourName")

        val tourDescription = tourData.getStringExtra("tourDescription")
//        val topTourDescription = tourData.getStringExtra("topTourDescription")

        val tourDifficulty = tourData.getStringExtra("tourDifficulty")
        val tourPrice = tourData.getStringExtra("tourPrice")
//        val topTourPrice = tourData.getStringExtra("topTourPrice")

        val tourRating = tourData.getStringExtra("tourRating")
//        val topTourRating = tourData.getStringExtra("topTourRating")

        val tourImg = tourData.getIntExtra("tourImg", 0)

        txt_nameDetail.text = tourName
//        txt_nameDetail.text = topTourName
        txt_description.text = tourDescription
//        txt_description.text = topTourDescription
//        txt_difficult.text = tourDifficulty
        if (tourRating != null) {
            rb_tourDetail.rating = tourRating.toFloat()
        }
//        if (topTourRating != null) {
//            rb_tourDetail.rating = topTourRating.toFloat()
//        }

        txt_tourPriceDetail.text = tourPrice
//        txt_tourPriceDetail.text = topTourPrice
//        img_tourDetail.setImageResource(tourImg)

        btnBookTour.setOnClickListener {
            val intent = Intent(this@DetailActivity, CheckActivity::class.java)
            startActivity(intent)
        }
        imgBack.setOnClickListener {
            this.finish()
        }
    }

}