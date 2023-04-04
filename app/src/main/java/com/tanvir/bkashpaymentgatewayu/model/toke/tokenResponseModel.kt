package com.tanvir.bkashpaymentgatewayu.model.toke

data class tokenResponseModel(val statusCode:String,val statusMessage:String,val id_token:String,val token_type:String,
                              val expires_in:String,val refresh_token:String)