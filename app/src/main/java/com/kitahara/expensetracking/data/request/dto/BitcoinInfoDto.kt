package com.kitahara.expensetracking.data.request.dto

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class BitcoinInfoDto(
    @SerialName("bpi")
    val bpi: Bpi? = null,
    @SerialName("chartName")
    val chartName: String? = null, // Bitcoin
    @SerialName("disclaimer")
    val disclaimer: String? = null, // This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org
    @SerialName("time")
    val time: Time? = null
)