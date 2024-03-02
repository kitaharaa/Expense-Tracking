package com.kitahara.expensetracking.domain.repo

import androidx.paging.PagingData
import com.kitahara.expensetracking.domain.entity.TransactionData
import kotlinx.coroutines.flow.Flow

interface MyPagingData {
    fun getPagingTransactions(): Flow<PagingData<TransactionData>>
}