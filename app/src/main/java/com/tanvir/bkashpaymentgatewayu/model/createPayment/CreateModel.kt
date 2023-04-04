package com.tanvir.bkashpaymentgatewayu.model.createPayment

data class CreateModel(val mode:String,val payerReference:String,val callbackURL:String,val merchantAssociationInfo:String,val amount:String,val currency:String,val intent:String,
                       val merchantInvoiceNumber:String)
