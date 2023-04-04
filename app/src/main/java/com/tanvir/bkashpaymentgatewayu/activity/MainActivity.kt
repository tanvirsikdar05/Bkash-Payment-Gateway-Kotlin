package com.tanvir.bkashpaymentgatewayu.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.tanvir.bkashpaymentgatewayu.R
import com.tanvir.bkashpaymentgatewayu.Service.apiInterface
import com.tanvir.bkashpaymentgatewayu.Service.apiUtils
import com.tanvir.bkashpaymentgatewayu.model.createPayment.CreateModel
import com.tanvir.bkashpaymentgatewayu.model.createPayment.createResponseModel
import com.tanvir.bkashpaymentgatewayu.model.toke.tokenModel
import com.tanvir.bkashpaymentgatewayu.model.toke.tokenResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var payButton:Button
    lateinit var price:EditText
    private val apiUtils = apiUtils()
    lateinit var apiinterface:apiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        payButton=findViewById(R.id.buttonid)
        price=findViewById(R.id.edtprice)
        apiinterface = apiUtils.api


        payButton.setOnClickListener {
            val model = tokenModel(
                "4f6o0cjiki2rfm34kfdadl1eqq",
                "2is7hdktrekvrbljjh44ll3d9l1dtjo4pasmjvs5vl5qr3fug4b"
            )
            val response: Call<tokenResponseModel?>? = apiinterface.getToken(
                "application/json",
                "application/json",
                "sandboxTokenizedUser02",
                "sandboxTokenizedUser02@12345",
                model
            )
            response!!.enqueue(object : Callback<tokenResponseModel?> {
                override fun onResponse(call: Call<tokenResponseModel?>,response: Response<tokenResponseModel?>) {
                    if (response.isSuccessful()) {
                        val token: String = response.body()!!.id_token
                        val dectoken: String = response.body()!!.token_type + " " + token
                        Log.e("token", token)
                        getUiUrl(dectoken)
                    } else {
                        Log.e("token", response.toString())
                    }
                }

                override fun onFailure(call: Call<tokenResponseModel?>, t: Throwable) {
                    Log.e("error","cant get")
                }
            })
        }
    }
    fun getUiUrl(token:String){
        val newPrice: String = price.text.toString()
        val modelData = CreateModel(
            "0011", "", "https://app.masbaahmed.com/api/bkash/callback",
            "MI05MID54RF09123456One", newPrice, "BDT", "sale", "inv001"
        )
        val paymentResponse: Call<createResponseModel?>? = apiinterface.getPaymentResponse(
            "application/json",
            "application/json",
            token,
            "4f6o0cjiki2rfm34kfdadl1eqq",
            modelData
        )
        paymentResponse!!.enqueue(object : Callback<createResponseModel?> {
            override fun onFailure(call: Call<createResponseModel?>, t: Throwable) {
                Log.e("token", t.message!!)
            }

            override fun onResponse(
                call: Call<createResponseModel?>,
                response: Response<createResponseModel?>
            ) {
                if (response.isSuccessful()) {
                    val uiUrl: String = response.body()!!.bkashURL
                    val paymentId: String = response.body()!!.paymentID
                    Log.e("token", uiUrl)
                    startActivity(
                        Intent(this@MainActivity, webviewAct::class.java).putExtra(
                            "url",
                            uiUrl
                        ).putExtra("id", paymentId).putExtra("token", token)
                    )
                } else {
                    Log.e("token", response.toString())
                }
            }
        })
    }
}


