package com.kitahara.expensetracking.di.modules

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.PortUnreachableException
import java.net.ProtocolException
import java.net.SocketException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class KtorModule {
    private val attempts = 3
    private val delay = 5L
    private val connectionTime = 30000L

    @Provides
    @Singleton
    fun provideKtorClient() = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL

            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("Ktor", message)
                }
            }
        }

        install(ContentNegotiation) {
            json()
        }

        install(HttpTimeout) {
            connectTimeoutMillis = connectionTime
            requestTimeoutMillis = connectionTime
        }

        install(HttpRequestRetry) {
            maxRetries = attempts
            retryOnException(attempts)
            retryOnServerErrors(attempts)
            retryIf { _, response ->
                response.status.isSuccess().not()
            }
            retryOnExceptionIf { _, cause ->
                (cause is UnknownHostException) or
                        (cause is SocketTimeoutException) or
                        (cause is ConnectException) or
                        (cause is NoRouteToHostException) or
                        (cause is PortUnreachableException) or
                        (cause is ProtocolException) or
                        (cause is SocketException) or
                        (cause is Exception)
            }

            delayMillis { retry ->
                retry * TimeUnit.SECONDS.toMillis(delay)
            }
        }
    }
}