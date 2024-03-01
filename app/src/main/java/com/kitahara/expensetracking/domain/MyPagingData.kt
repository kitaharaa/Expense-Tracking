package com.kitahara.expensetracking.domain

import androidx.paging.PagingData
import com.kitahara.expensetracking.domain.paging.entity.TransactionData
import kotlinx.coroutines.flow.Flow

interface MyPagingData {
    fun getPagingTransactions(): Flow<PagingData<TransactionData>>
}