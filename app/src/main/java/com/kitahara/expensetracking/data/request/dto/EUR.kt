package com.kitahara.expensetracking.data.request.dto


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class EUR(
    @SerialName("code")
    val code: String? = null, // EUR
    @SerialName("description")
    val description: String? = null, // Euro
    @SerialName("rate")
    val rate: String? = null, // 57,319.676
    @SerialName("rate_float")
    val rateFloat: Double? = null, // 57319.6764
    @SerialName("symbol")
    val symbol: String? = null // &euro;
)