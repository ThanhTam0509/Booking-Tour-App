package com.thanhtam.bookingtour.data.responses

data class Location(
    val _id: String,
    val coordinates: List<Double>,
    val day: Int,
    val description: String,
    val type: String
)