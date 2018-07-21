package com.example.credit.creditForm.dataManager

import com.google.gson.annotations.SerializedName

data class CreditModel(@SerializedName("cardNo") val cardNo: String = "",
                       @SerializedName("expiryMonth") val expiryMonth: Int = 0,
                       val cvv: Int = 0,
                       @SerializedName("expiryYear") val expiryYear: Int = 0,
                       val name: String = "")

data class CreditResponse(@SerializedName("success") val success: Boolean,
                          @SerializedName("data") val data: ResponseData
)

data class ResponseData(@SerializedName("requestId") val requestId: Long,
                        @SerializedName("name") val name: String,
                        @SerializedName("requestDate") val requestDate: Long)