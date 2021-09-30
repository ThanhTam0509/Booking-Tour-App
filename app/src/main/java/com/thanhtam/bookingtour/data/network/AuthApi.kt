package com.thanhtam.bookingtour.data.network

import com.thanhtam.bookingtour.data.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {


    @FormUrlEncoded
    @POST("http://192.168.1.4:3000/api/v1/users/login/")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : LoginResponse
}