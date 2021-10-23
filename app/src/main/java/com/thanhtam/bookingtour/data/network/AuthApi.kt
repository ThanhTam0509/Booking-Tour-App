package com.thanhtam.bookingtour.data.network

import com.thanhtam.bookingtour.data.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {


    @FormUrlEncoded
    @POST("api/v1/users/login/")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : LoginResponse
}