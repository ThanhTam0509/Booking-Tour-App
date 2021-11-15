package com.thanhtam.bookingtour.ui

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanhtam.bookingtour.data.network.RemoteDataSource
import com.thanhtam.bookingtour.data.network.TourApi
import com.thanhtam.bookingtour.data.repository.TourRepository
import com.thanhtam.bookingtour.data.responses.ResponseTour
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

/*
///
/// Project: Booking Tour
/// Created by babyd on 11/3/2021.
/// Copyright © 2018-2019 Beeknights Co., Ltd. All rights reserved.
///
*/

@HiltViewModel
class BottomActivityViewModel @Inject constructor(
) : ViewModel() {
    val recyclerListData: MutableLiveData<ResponseTour> = MutableLiveData()

    fun getRecyclerListDataObserver(): MutableLiveData<ResponseTour> {
        return recyclerListData
    }

    val _recyclerListData: MutableLiveData<ResponseTour> = MutableLiveData()

    fun _getRecyclerListDataObserver(): MutableLiveData<ResponseTour> {
        return _recyclerListData
    }

    fun makeApiCallTourCheap() {
        val remoteDataSource = RemoteDataSource.getRemoteDataSource().create(TourApi::class.java)
        val call = remoteDataSource.getTour()
        call.enqueue(object :
            retrofit2.Callback<ResponseTour> {
            @SuppressLint("NotifyDataSetChanged", "NullSafeMutableLiveData")
            override fun onResponse(
                call: Call<ResponseTour>,
                response: Response<ResponseTour>
            ) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }

            @SuppressLint("NullSafeMutableLiveData")
            override fun onFailure(call: Call<ResponseTour>, t: Throwable) {
                recyclerListData.postValue(null)
            }
        })
    }

    fun makeApiCallAllTour() {
        val remoteDataSource = RemoteDataSource.getRemoteDataSource().create(TourApi::class.java)
        val call = remoteDataSource.getAllTour()
        call.enqueue(object :
            retrofit2.Callback<ResponseTour> {
            @SuppressLint("NotifyDataSetChanged", "NullSafeMutableLiveData")
            override fun onResponse(
                call: Call<ResponseTour>,
                response: Response<ResponseTour>
            ) {
                if (response.isSuccessful) {
                    _recyclerListData.postValue(response.body())
                } else {
                    _recyclerListData.postValue(null)
                }
            }

            @SuppressLint("NullSafeMutableLiveData")
            override fun onFailure(call: Call<ResponseTour>, t: Throwable) {
                _recyclerListData.postValue(null)
            }
        })
    }
}

