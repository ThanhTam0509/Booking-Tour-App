package com.thanhtam.bookingtour.data.responses

data class LoginResponse(
    val `data`: Data,
    val status: String,
    val token: String
)