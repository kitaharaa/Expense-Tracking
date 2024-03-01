package com.kitahara.expensetracking.domain.repo

import kotlinx.coroutines.flow.Flow

interface BalanceSource {
    fun getBitcoinAmountFlow(): Flow<Float>

    suspend fun addCost(sum: Float)

    suspend fun getBitcoinBalanceAmount(): Float
}