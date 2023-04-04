package com.tanvir.bkashpaymentgatewayu.model.createPayment

data class createResponseModel(val statusCode:String,val statusMessage:String,val paymentID:String,val bkashURL:String,val callbackURL:String,val successCallbackURL:String,
                               val failureCallbackURL:String,val cancelledCallbackURL:String,val amount:String,val intent:String,val currency:String,val paymentCreateTime:String,
                               val transactionStatus:String,val merchantInvoiceNumber:String)
