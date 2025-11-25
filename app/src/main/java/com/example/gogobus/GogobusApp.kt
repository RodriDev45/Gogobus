package com.example.gogobus

import android.app.Application
import com.mercadopago.sdk.android.domain.model.CountryCode
import com.mercadopago.sdk.android.initializer.MercadoPagoSDK
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GogobusApp: Application() {
    override fun onCreate() {
        super.onCreate()
        MercadoPagoSDK.initialize(
            context = this,
            publicKey = "APP_USR-5e19a902-1455-4801-9bf3-d2452f96aef9",
            countryCode = CountryCode.PER
        )
    }
}