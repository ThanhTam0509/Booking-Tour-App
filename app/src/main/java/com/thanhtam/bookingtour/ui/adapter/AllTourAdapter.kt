package com.thanhtam.bookingtour.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hendraanggrian.pikasso.picasso
import com.hendraanggrian.pikasso.transformations.grayscale
import com.hendraanggrian.pikasso.transformations.rounded
import com.thanhtam.bookingtour.R
import com.thanhtam.bookingtour.data.responses.ResponseTour
import com.thanhtam.bookingtour.ui.DetailActivity
import kotlinx.android.synthetic.main.item_tour.view.*

/*
///
/// Project: Booking Tour
/// Created by babyd on 10/30/2021.
/// Copyright © 2018-2019 Beeknights Co., Ltd. All rights reserved.
///
*/
class AllTourAdapter(val data: ResponseTour, var c: Context) : RecyclerView.Adapter<AllTourAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tour, parent, false)
        )
    }

    override fun getItemCount() = data.data.data.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = data.data.data[position]
        holder.view.txt_nameTour.text = data.name
        holder.view.txt_difficult.text = data.difficulty
        holder.view.txt_priceTour.text = data.price.toString() + " " + "USD"
        holder.view.rb_Tour.rating = data.ratingsAverage.toFloat()
        holder.view.setOnClickListener {
            var allTourIntent = Intent(c, DetailActivity::class.java)
            allTourIntent.putExtra("tourName", data.name)
            allTourIntent.putExtra("tourDifficulty", data.difficulty)
            allTourIntent.putExtra("tourPrice", data.price)
            allTourIntent.putExtra("tourRating", data.ratingsAverage)
            allTourIntent.putExtra("tourDescription", data.description)
            allTourIntent.putExtra("tourImg", data.imageCover)
            c.startActivity(allTourIntent)
        }

        picasso.load("https://server-bookingtour.herokuapp.com/img/tours/${data.imageCover}")
            .into(holder.view.img_Tour)

    }

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}