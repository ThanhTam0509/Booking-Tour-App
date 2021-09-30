package com.thanhtam.bookingtour.data.repository

import com.thanhtam.bookingtour.data.UserPreferences
import com.thanhtam.bookingtour.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }


    suspend fun saveAuthToken(token: String){
        preferences.saveAuthToken(token)
    }
}