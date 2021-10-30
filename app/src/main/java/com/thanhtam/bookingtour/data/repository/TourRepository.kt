package com.thanhtam.bookingtour.data.repository

import com.thanhtam.bookingtour.data.network.TourApi
import javax.inject.Inject
import javax.inject.Singleton

/*
///
/// Project: Booking Tour
/// Created by babyd on 10/28/2021.
/// Copyright © 2018-2019 Beeknights Co., Ltd. All rights reserved.
///
*/
@Singleton
class TourRepository @Inject constructor(
    private val api: TourApi
) : BaseRepository() {

    suspend fun getTour(
    ) = safeApiCall {
        api.getTour()
    }
    suspend fun getAllTour(

    ) = safeApiCall {
        api.getAllTour()
    }
}