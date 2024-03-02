package com.kitahara.expensetracking.data.request.dto

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class GBP(
    @SerialName("code")
    val code: String? = null, // GBP
    @SerialName("description")
    val description: String? = null, // British Pound Sterling
    @SerialName("rate")
    val rate: String? = null, // 49,092.123
    @SerialName("rate_float")
    val rateFloat: Double? = null, // 49092.1227
    @SerialName("symbol")
    val symbol: String? = null // &pound;
)