package com.tanvir.bkashpaymentgatewayu.activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tanvir.bkashpaymentgatewayu.R
import com.tanvir.bkashpaymentgatewayu.Service.apiInterface
import com.tanvir.bkashpaymentgatewayu.Service.apiUtils
import com.tanvir.bkashpaymentgatewayu.model.verifyPaymentModel
import retrofit2.Call
import retrofit2.Response

class webviewAct : AppCompatActivity() {
    lateinit var webview:WebView
    var paymentId: String? = null
    var authToken:String? = null
    private val apiUtils = apiUtils()
    lateinit var apiinterface: apiInterface
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        webview=findViewById(R.id.webviewid)
        apiinterface = apiUtils.api
        val url = intent.getStringExtra("url")
        paymentId = intent.getStringExtra("id")
        authToken = intent.getStringExtra("token")

        webview.setWebViewClient(Callback())
        val webSettings: WebSettings = webview.settings
        webSettings.javaScriptEnabled = true
        url?.let { webview.loadUrl(it) }

    }

    inner class Callback : WebViewClient() {
        override fun onReceivedError(
            view: WebView,
            errorCode: Int,
            description: String,
            failingUrl: String
        ) {
            Log.e("urls", "error $failingUrl")
        }


        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            if (url!!.contains("https://app.masbaahmed.com/api/bkash/callback")) {
                view!!.stopLoading()
                CheckSuccessorFail()

            }
        }

        override fun onPageFinished(view: WebView, url: String) {
            Log.e("urls", "finish $url")
        }

    }
     private fun CheckSuccessorFail() {
        val response: Call<verifyPaymentModel?>? = apiinterface.checkStatus(
            "application/json",
            authToken,
            "4f6o0cjiki2rfm34kfdadl1eqq",
            paymentId
        )
        response!!.enqueue(object : retrofit2.Callback<verifyPaymentModel?> {
            override fun onResponse(
                call: Call<verifyPaymentModel?>,
                response: Response<verifyPaymentModel?>
            ) {
                if (response.isSuccessful()) {
                    //if (response.body().getStatusMessage().equals("Successful")){
                    Toast.makeText(this@webviewAct, "Payment Successful", Toast.LENGTH_SHORT).show()
                    //}else Toast.makeText(webviewAct.this, "Payment Fails", Toast.LENGTH_SHORT).show();
                }
            }

            override fun onFailure(call: Call<verifyPaymentModel?>, t: Throwable) {}
        })
    }

}