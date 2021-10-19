package com.thanhtam.bookingtour.ui.auth.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanhtam.bookingtour.data.network.Resource
import com.thanhtam.bookingtour.data.repository.UserRepository
import com.thanhtam.bookingtour.data.responses.LoginResponse
import kotlinx.coroutines.launch

/*
///
/// Project: Booking Tour
/// Created by babyd on 10/18/2021.
/// Copyright © 2018-2019 Beeknights Co., Ltd. All rights reserved.
///
*/
class HomeViewModel(
    private val repository: UserRepository

) : ViewModel() {

    private val _user: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user: LiveData<Resource<LoginResponse>>
        get() = _user

    fun getUser(
    ) = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }

}