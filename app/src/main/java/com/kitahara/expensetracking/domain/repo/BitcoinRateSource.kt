package com.kitahara.expensetracking.domain.repo

import kotlinx.coroutines.flow.Flow

interface BitcoinRateSource {
    fun getBitcoinToUsdRateFlow(): Flow<Float>

    suspend fun updateBitcoinRate()
}