package com.kitahara.expensetracking.domain

import kotlinx.coroutines.flow.Flow

interface BitcoinRateSource {
    fun getBitcoinToUsdRateFlow(): Flow<Float>
}