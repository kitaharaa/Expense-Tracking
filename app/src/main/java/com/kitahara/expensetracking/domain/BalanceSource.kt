package com.kitahara.expensetracking.domain

import kotlinx.coroutines.flow.Flow

interface BalanceSource {
    fun getBitcoinAmountFlow(): Flow<Float>

    suspend fun replenishBalance(sum: Float)
}