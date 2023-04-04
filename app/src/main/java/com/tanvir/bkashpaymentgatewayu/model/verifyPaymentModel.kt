package com.tanvir.bkashpaymentgatewayu.model

data class verifyPaymentModel(val statusCode:String,val statusMessage:String,val paymentID:String,val payerReference:String,val customerMsisdn:String,val trxID:String,
                              val amount:String,val transactionStatus:String,val paymentExecuteTime:String,val currency:String,val intent:String,val merchantInvoiceNumber:String)