package com.thanhtam.bookingtour.data.repository

import com.thanhtam.bookingtour.data.UserPreferences
import com.thanhtam.bookingtour.data.network.AuthApi
import com.thanhtam.bookingtour.data.network.UserApi
import com.thanhtam.bookingtour.data.responses.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi
) : BaseRepository() {

    suspend fun getUser(
    ) = safeApiCall {
        api.getUser()
    }
}