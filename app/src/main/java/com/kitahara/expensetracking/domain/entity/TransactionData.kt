package com.kitahara.expensetracking.domain.entity

//wrapper of TransactionEntity
data class TransactionData(
    val id: Int,
    val operationDate: String,
    val spendAmount: Float,
    val category: String
)