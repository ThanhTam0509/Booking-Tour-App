package com.thanhtam.bookingtour.data.repository

import com.thanhtam.bookingtour.data.UserPreferences
import com.thanhtam.bookingtour.data.network.AuthApi
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }

    suspend fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirm: String
    ) = safeApiCall {
        api.register(name, email, password, passwordConfirm)
    }

    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }
}