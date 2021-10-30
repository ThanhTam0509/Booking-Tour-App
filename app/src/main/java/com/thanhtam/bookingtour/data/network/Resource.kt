package com.thanhtam.bookingtour.data.network

import com.thanhtam.bookingtour.data.responses.ResponseTour
import okhttp3.ResponseBody

sealed class Resource<out T> : com.bumptech.glide.load.engine.Resource<ResponseTour> {
    data class Success<out T>(val value: T): Resource<T>()
    data class Failure(
        val isNetWorkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()
    object Loading: Resource<Nothing>()

    override fun getResourceClass(): Class<ResponseTour> {
        TODO("Not yet implemented")
    }

    override fun get(): ResponseTour {
        TODO("Not yet implemented")
    }

    override fun getSize(): Int {
        TODO("Not yet implemented")
    }

    override fun recycle() {
        TODO("Not yet implemented")
    }
}