package com.thanhtam.bookingtour.ui

import android.app.Application
import com.stripe.android.PaymentConfiguration

/*
///
/// Project: Booking Tour
/// Created by babyd on 11/24/2021.
/// Copyright © 2018-2019 Beeknights Co., Ltd. All rights reserved.
///
*/class FirebaseMobilePaymentsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        PaymentConfiguration.init(
            applicationContext,
            "pk_test_qblFNYngBkEdjEZ16jxxoWSM"
        )
    }
}