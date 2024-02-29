package com.kitahara.expensetracking.data.request.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

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