package com.kitahara.expensetracking.data.request.dto


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class USD(
    @SerialName("code")
    val code: String? = null, // USD
    @SerialName("description")
    val description: String? = null, // United States Dollar
    @SerialName("rate")
    val rate: String? = null, // 61,921.831
    @SerialName("rate_float")
    val rateFloat: Float? = null, // 61921.8307
    @SerialName("symbol")
    val symbol: String? = null // &#36;
)