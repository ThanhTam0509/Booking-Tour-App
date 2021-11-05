package com.thanhtam.bookingtour.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanhtam.bookingtour.data.network.Resource
import com.thanhtam.bookingtour.data.repository.AuthRepository
import com.thanhtam.bookingtour.data.responses.LoginResponse
import com.thanhtam.bookingtour.data.responses.RegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    private val _registerResponse: MutableLiveData<Resource<RegisterResponse>> = MutableLiveData()

    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    val registerResponse: LiveData<Resource<RegisterResponse>>
        get() = _registerResponse

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(email, password)
    }

    fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirm: String
    ) = viewModelScope.launch {
        _registerResponse.value = repository.register(name, email, password, passwordConfirm)
    }

    fun saveAuthToken(token: String) = viewModelScope.launch {
        repository.saveAuthToken(token)
    }
}