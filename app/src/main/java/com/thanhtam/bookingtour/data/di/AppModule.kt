package com.thanhtam.bookingtour.data.di

import com.thanhtam.bookingtour.data.network.AuthApi
import com.thanhtam.bookingtour.data.network.RemoteDataSource
import com.thanhtam.bookingtour.data.network.TourApi
import com.thanhtam.bookingtour.data.network.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/*
///
/// Project: Booking Tour
/// Created by babyd on 10/27/2021.
/// Copyright © 2018-2019 Beeknights Co., Ltd. All rights reserved.
///
*/

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAuthApi(
        remoteDataSource: RemoteDataSource,
    ): AuthApi {
        return remoteDataSource.buildApi(AuthApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUserApi(
        remoteDataSource: RemoteDataSource,
    ): UserApi {
        return remoteDataSource.buildApi(UserApi::class.java)
    }
    @Singleton
    @Provides
    fun provideTourApi(
        remoteDataSource: RemoteDataSource,
    ): TourApi {
        return remoteDataSource.buildApi(TourApi::class.java)
    }
}