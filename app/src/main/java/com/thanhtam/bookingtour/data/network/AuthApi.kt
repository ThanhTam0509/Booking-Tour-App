package com.thanhtam.bookingtour.data.network

import com.thanhtam.bookingtour.data.responses.LoginResponse
import com.thanhtam.bookingtour.data.responses.RegisterResponse
import com.thanhtam.bookingtour.data.responses.ResponseUser
import retrofit2.http.*

interface AuthApi {


    @FormUrlEncoded
    @POST("api/v1/users/login/")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : LoginResponse

    @POST("api/v1/users/signup/")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("passwordConfirm") passwordConfirm: String
    ) : RegisterResponse
}