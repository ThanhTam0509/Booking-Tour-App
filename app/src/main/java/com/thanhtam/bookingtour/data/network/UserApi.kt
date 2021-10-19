package com.thanhtam.bookingtour.data.network

import com.thanhtam.bookingtour.data.responses.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

/*
///
/// Project: Booking Tour
/// Created by babyd on 10/18/2021.
/// Copyright © 2018-2019 Beeknights Co., Ltd. All rights reserved.
///
*/

interface UserApi {


    @GET("users/")
    suspend fun getUser(): LoginResponse
}