package com.tanvir.bkashpaymentgatewayu.Service

import com.tanvir.bkashpaymentgatewayu.model.createPayment.CreateModel
import com.tanvir.bkashpaymentgatewayu.model.createPayment.createResponseModel
import com.tanvir.bkashpaymentgatewayu.model.toke.tokenModel
import com.tanvir.bkashpaymentgatewayu.model.toke.tokenResponseModel
import com.tanvir.bkashpaymentgatewayu.model.verifyPaymentModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface apiInterface {
    @POST("tokenized/checkout/token/grant")
    fun getToken(
        @Header("Content-Type") acval: String?,
        @Header("Accept") Aceptval: String?,
        @Header("username") username: String?,
        @Header("password") pass: String?,
        @Body modeldata: tokenModel?
    ): Call<tokenResponseModel?>?

    @POST("tokenized/checkout/create")
    fun getPaymentResponse(
        @Header("Content-Type") acval: String?,
        @Header("Accept") Acval: String?,
        @Header("Authorization") auth: String?,
        @Header("X-App-Key") xappkey: String?,
        @Body modelData: CreateModel?
    ): Call<createResponseModel?>?

    @POST("tokenized/checkout/execute")
    fun checkStatus(
        @Header("Accept") Acval: String?,
        @Header("Authorization") auth: String?,
        @Header("X-App-Key") xappkey: String?,
        @Body paymentid: String?
    ): Call<verifyPaymentModel?>?
}