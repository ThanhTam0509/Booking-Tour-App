//package com.thanhtam.bookingtour.ui.adapter
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.AdapterView
//import android.widget.TextView
//import androidx.lifecycle.ViewModel
//import androidx.recyclerview.widget.RecyclerView
//import com.thanhtam.bookingtour.R
//
///*
/////
///// Project: Booking Tour
///// Created by babyd on 10/25/2021.
///// Copyright © 2018-2019 Beeknights Co., Ltd. All rights reserved.
/////
//*/
//class SearchApdater(val listData: List<ViewModel>, val clickListener: ClickListener): RecyclerView.Adapter<SearchApdater.MyViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchApdater.MyViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
//
//        return MyViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ???, position: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getItemCount(): Int {
//        return listData.size
//    }
//    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
//        var titleTv: TextView
//
//        init {
//            titleTv = view.findViewById(R.id.titleTv)
//        }
//    }
//
//    override fun onBindViewHolder(
//        holder: SearchApdater,
//        position: Int,
//        payloads: MutableList<Any>
//    ) {
//        holder.titleTv.text = listData.get(position).title
//        holder.itemView
//    }
//    interface ClickListener {
//        fun onItemClick(dataModel: ViewModel)
//    }
//}