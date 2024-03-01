package com.kitahara.expensetracking.domain.repo

interface TransactionSource {
    suspend fun saveTransaction(
        category: String?,
        amount: Float,
    )
}