package com.thanhtam.bookingtour.data.responses

data class User(
    val _id: String,
    val email: String,
    val name: String,
    val photo: String,
    val role: String
)