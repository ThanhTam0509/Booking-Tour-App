package com.thanhtam.bookingtour.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hendraanggrian.pikasso.picasso
import com.hendraanggrian.pikasso.transformations.grayscale
import com.hendraanggrian.pikasso.transformations.rounded
import com.thanhtam.bookingtour.R
import com.thanhtam.bookingtour.data.responses.ResponseTour
import com.thanhtam.bookingtour.databinding.ItemTourTop5CheapBinding
import kotlinx.android.synthetic.main.item_tour_top_5_cheap.view.*

/*
///
/// Project: Booking Tour
/// Created by babyd on 10/25/2021.
/// Copyright © 2018-2019 Beeknights Co., Ltd. All rights reserved.
///
*/
class TourCheapAdapter(val data: ResponseTour) :
    RecyclerView.Adapter<TourCheapAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemTourTop5CheapBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, parent.context)
    }

    override fun getItemCount() = data.data.data.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = data.data.data[position]
        holder.binding.txtNameTourCheap.text = data.name
        holder.binding.txtDifficultCheap.text = data.difficulty
        holder.binding.txtPriceTourCheap.text = data.price.toString() + " " + "USD"
        holder.binding.rbTourCheap.rating = data.ratingsAverage.toFloat()

        picasso.load("https://server-bookingtour.herokuapp.com/img/tours/${data.imageCover}")
            .into(holder.binding.imgTourCheap)
    }

    inner class MyViewHolder(val binding: ItemTourTop5CheapBinding, val context: Context) : RecyclerView.ViewHolder(binding.root){


    }
}