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
        val tourDescription = tourData.getStringExtra("tourDescription")
        val tourDifficulty = tourData.getStringExtra("tourDifficulty")
        val tourPrice = tourData.getStringExtra("tourPrice")
        val tourRating = tourData.getStringExtra("tourRating")
        val tourImg = tourData.getIntExtra("tourImg", 0)

        txt_nameDetail.text = tourName
        txt_description.text = tourDescription
//        txt_difficult.text = tourDifficulty
        if (tourRating != null) {
            rb_tourDetail.rating = tourRating.toFloat()
        }
        txt_tourPriceDetail.text = tourPrice
//        img_tourDetail.setImageResource(tourImg)


        btnBookTour.setOnClickListener {
            val intent = Intent(this@DetailActivity, CheckActivity::class.java)
            startActivity(intent)
        }
    }
}