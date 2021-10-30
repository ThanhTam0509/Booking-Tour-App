package com.thanhtam.bookingtour.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.thanhtam.bookingtour.R
import com.thanhtam.bookingtour.data.responses.ResponseTour
import kotlinx.android.synthetic.main.item_tour.view.*
import kotlinx.android.synthetic.main.item_tour_top_5_cheap.view.*
import kotlinx.android.synthetic.main.item_tour_top_5_cheap.view.txt_nameTour_cheap

/*
///
/// Project: Booking Tour
/// Created by babyd on 10/30/2021.
/// Copyright © 2018-2019 Beeknights Co., Ltd. All rights reserved.
///
*/
class AllTourAdapter(val data : ResponseTour) : RecyclerView.Adapter<AllTourAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tour, parent, false)
        )
    }

    override fun getItemCount() = data.data.data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = data.data.data[position]
        holder.view.txt_nameTour.text = data.name
        holder.view.txt_difficult.text = data.difficulty
        holder.view.txt_priceTour.text = data.price.toString()  + " " + "USD"
        holder.view.rb_Tour.rating = data.ratingsAverage.toFloat()

        Glide.with(holder.view.context)
            .load(data.imageCover)
            .into(holder.view.img_Tour)

        val url = data.imageCover
        Picasso.get()
            .load(url)
            .into(holder.view.img_Tour)
    }
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}