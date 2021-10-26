package com.thanhtam.bookingtour.data.network

import com.thanhtam.bookingtour.data.responses.ResponseDeleteUser
import com.thanhtam.bookingtour.data.responses.ResponseUpdateUser
import com.thanhtam.bookingtour.data.responses.ResponseUser
import retrofit2.http.*

/*
///
/// Project: Booking Tour
/// Created by babyd on 10/26/2021.
/// Copyright © 2018-2019 Beeknights Co., Ltd. All rights reserved.
///
*/interface UserApi {
    @GET("api/v1/users/me")
    suspend fun currentMe(@Header("Authorization") authorization: String): ResponseUser

    @PATCH("api/v1/users/updateMe")
    suspend fun updateMe (
        @Header("Authorization") authorization: String,
        @Field ("name") name: String,
        @Field ("email") email: String
    ): ResponseUpdateUser
}