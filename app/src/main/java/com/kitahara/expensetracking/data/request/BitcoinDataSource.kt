package com.kitahara.expensetracking.data.request

import android.util.Log
import com.kitahara.expensetracking.data.request.dto.BitcoinInfoDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path
import javax.inject.Inject

class BitcoinDataSource @Inject constructor(
    private val httpClient: HttpClient
) {


    suspend fun getBitcoinInfo(): BitcoinInfoDto? =
        try {
            httpClient.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.coindesk.com"
                    path("v1/bpi/currentprice.json")
                }
            }.body<BitcoinInfoDto?>()

        } catch (e: Exception) {
            Log.e("BitcoinState", e.message.toString())

            null
        }
}