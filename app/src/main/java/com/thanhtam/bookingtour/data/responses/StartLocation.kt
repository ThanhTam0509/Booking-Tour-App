package com.thanhtam.bookingtour.data.responses

data class StartLocation(
    val address: String,
    val coordinates: List<Double>,
    val description: String,
    val type: String
)