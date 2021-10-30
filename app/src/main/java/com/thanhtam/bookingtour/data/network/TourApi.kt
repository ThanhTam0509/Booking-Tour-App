package com.thanhtam.bookingtour.data.network

import com.thanhtam.bookingtour.data.responses.DataXX
import com.thanhtam.bookingtour.data.responses.ResponseTour
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/*
///
/// Project: Booking Tour
/// Created by babyd on 10/27/2021.
/// Copyright © 2018-2019 Beeknights Co., Ltd. All rights reserved.
///
*/
const val BASE_URL = "https://server-bookingtour.herokuapp.com/"

interface TourApi {


    //    @Headers("Accept-Version: v1", "")
    @GET("api/v1/tours/top-5-cheap")
    fun getTour(): Call<ResponseTour>

    @GET("api/v1/tours")
    fun getAllTour(): Call<ResponseTour>

    companion object {
        operator fun invoke(): TourApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TourApi::class.java)
        }
    }
}