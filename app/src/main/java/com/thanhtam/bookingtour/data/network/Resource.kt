package com.thanhtam.bookingtour.data.network

import com.thanhtam.bookingtour.data.responses.ResponseTour
import okhttp3.ResponseBody

/*
* Success, Failure, Loading
* */

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetWorkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}