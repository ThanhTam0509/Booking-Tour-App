package com.thanhtam.bookingtour.data.network

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import org.json.JSONObject

/*
///
/// Project: Booking Tour
/// Created by babyd on 11/24/2021.
/// Copyright © 2018-2019 Beeknights Co., Ltd. All rights reserved.
///
*/class ApiClient {
    private val httpClient = OkHttpClient()

    fun createPaymentIntent(
        paymentMethodType: String,
        completion: (paymentIntentClientSecret: String?, error: String?) -> Unit) {

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestJson = """
            {
                "currency":"usd",
                "paymentMethodType":"$paymentMethodType"
            }
            """
        val body = requestJson.toRequestBody(mediaType)
        val request = Request.Builder()
            .url(BASE_URL + "create-payment-intent")
            .post(body)
            .build()
        httpClient.newCall(request)
            .enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    completion(null, "$e")
                }

                override fun onResponse(call: Call, response: Response) {
                    if (!response.isSuccessful) {
                        completion(null, "$response")
                    } else {
                        val responseData = response.body?.string()
                        val responseJson =
                            responseData?.let { JSONObject(it) } ?: JSONObject()

                        // The response from the server contains the PaymentIntent's client_secret
                        var paymentIntentClientSecret : String = responseJson.getString("clientSecret")
                        completion(paymentIntentClientSecret, null)
                    }
                }
            })
    }
}