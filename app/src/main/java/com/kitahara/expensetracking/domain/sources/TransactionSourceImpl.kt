package com.kitahara.expensetracking.domain.sources

import com.kitahara.expensetracking.data.local.database.dao.TransactionDao
import com.kitahara.expensetracking.data.local.entity.TransactionEntity
import com.kitahara.expensetracking.domain.repo.TransactionSource
import java.util.Date
import javax.inject.Inject

//Saving transactions
class TransactionSourceImpl @Inject constructor(
    private val transactionDao: TransactionDao,
) : TransactionSource {

    override suspend fun saveTransaction(
        category: String?,
        amount: Float,
    ) {
        if (category == null) throw Exception("Choose category")

        transactionDao.addTransaction(
            TransactionEntity(
                operationDate = Date(System.currentTimeMillis()),
                spendAmount = amount,
                category = category
            )
        )
    }
}