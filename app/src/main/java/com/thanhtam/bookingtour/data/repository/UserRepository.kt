package com.thanhtam.bookingtour.data.repository

import com.thanhtam.bookingtour.data.network.UserApi
import javax.inject.Inject

/*
///
/// Project: Booking Tour
/// Created by babyd on 10/27/2021.
/// Copyright © 2018-2019 Beeknights Co., Ltd. All rights reserved.
///
*/
class UserRepository @Inject constructor(
    private val api: UserApi
) : BaseRepository() {

    suspend fun currentMe(
        authorization: String
    ) = safeApiCall {
        api.currentMe(authorization)
    }
}