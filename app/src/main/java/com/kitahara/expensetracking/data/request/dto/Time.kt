package com.kitahara.expensetracking.data.request.dto


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Time(
    @SerialName("updated")
    val updated: String? = null, // Feb 29, 2024 20:14:03 UTC
    @SerialName("updatedISO")
    val updatedISO: String? = null, // 2024-02-29T20:14:03+00:00
    @SerialName("updateduk")
    val updateduk: String? = null // Feb 29, 2024 at 20:14 GMT
)