package com.thanhtam.bookingtour.ui.booking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
///
/// Project: Booking Tour
/// Created by babyd on 10/25/2021.
/// Copyright © 2018-2019 Beeknights Co., Ltd. All rights reserved.
///
*/
class BookingViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Booking Fragment"
    }
    val text: LiveData<String> = _text
}