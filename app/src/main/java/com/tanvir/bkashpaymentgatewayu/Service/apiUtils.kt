package com.tanvir.bkashpaymentgatewayu.Service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class apiUtils {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://tokenized.sandbox.bka.sh/v1.2.0-beta/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api = retrofit.create(apiInterface::class.java)
}