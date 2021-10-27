package com.thanhtam.bookingtour.data.network

import com.thanhtam.bookingtour.data.responses.DataXX
import retrofit2.Call
import retrofit2.http.GET

/*
///
/// Project: Booking Tour
/// Created by babyd on 10/27/2021.
/// Copyright © 2018-2019 Beeknights Co., Ltd. All rights reserved.
///
*/interface TourApi {
    @GET("api/v1/tours")
    suspend fun getTour(): Call<List<DataXX>>
}