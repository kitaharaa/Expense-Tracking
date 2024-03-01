package com.kitahara.expensetracking.domain.paging.entity

data class TransactionData(
    val id: Int,
    val operationDate: String,
    val spendAmount: Float,
    val category: String
)