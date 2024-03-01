package com.kitahara.expensetracking.data.request.dto


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Bpi(
    @SerialName("EUR")
    val eUR: EUR? = null,
    @SerialName("GBP")
    val gBP: GBP? = null,
    @SerialName("USD")
    val uSD: USD? = null
)