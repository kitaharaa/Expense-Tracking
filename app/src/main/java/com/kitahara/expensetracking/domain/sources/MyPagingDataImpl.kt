package com.kitahara.expensetracking.domain.sources

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.kitahara.expensetracking.data.local.database.dao.TransactionDao
import com.kitahara.expensetracking.data.local.entity.TransactionEntity
import com.kitahara.expensetracking.domain.entity.TransactionData
import com.kitahara.expensetracking.domain.repo.MyPagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class MyPagingDataImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : MyPagingData {

    override fun getPagingTransactions(): Flow<PagingData<TransactionData>> =
        Pager(
            PagingConfig(
                pageSize = 20,
                prefetchDistance = 20
            ),
            pagingSourceFactory = transactionDao::getAllDataPaged
        )
            .flow
            .map { pagingData ->
                pagingData.map { entity ->
                    entity.convertToDomainModel()
                }
            }

    private fun TransactionEntity.convertToDomainModel(): TransactionData {
        val parsedTime = SimpleDateFormat("dd MMMM,\n HH:mm", Locale.US).format(operationDate)

        return TransactionData(
            id = id,
            operationDate = parsedTime,
            spendAmount, category
        )
    }
}