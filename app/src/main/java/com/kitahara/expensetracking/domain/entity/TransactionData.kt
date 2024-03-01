package com.kitahara.expensetracking.domain.entity

data class TransactionData(
    val id: Int,
    val operationDate: String,
    val spendAmount: Float,
    val category: String
)