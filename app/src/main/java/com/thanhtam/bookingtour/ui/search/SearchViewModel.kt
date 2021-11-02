package com.thanhtam.bookingtour.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanhtam.bookingtour.data.network.Resource
import com.thanhtam.bookingtour.data.repository.TourRepository
import com.thanhtam.bookingtour.data.responses.ResponseTour
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import javax.inject.Inject

/*
///
/// Project: Booking Tour
/// Created by babyd on 11/2/2021.
/// Copyright © 2018-2019 Beeknights Co., Ltd. All rights reserved.
///
*/
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: TourRepository
): ViewModel(){
    private val _responseTourCheap: MutableLiveData<Resource<Call<ResponseTour>>> = MutableLiveData()
    private val _responseAllTour: MutableLiveData<Resource<Call<ResponseTour>>> = MutableLiveData()

    val responseTourCheap: MutableLiveData<Resource<Call<ResponseTour>>>
        get() = _responseTourCheap

    val responseAllTours: MutableLiveData<Resource<Call<ResponseTour>>>
        get() = _responseAllTour

    fun getTour(
    ) = viewModelScope.launch {
        _responseTourCheap.value = repository.getTour()
    }
    fun getAllTours(

    ) = viewModelScope.launch {
        _responseAllTour.value = repository.getAllTour()
    }
}