package com.kitahara.expensetracking.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kitahara.expensetracking.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Upsert
    fun addTransaction(transactionEntity: TransactionEntity)

    @Query("SELECT * from transaction_table t ORDER BY t.operation_date")
    fun getAllTransactions(): Flow<TransactionEntity>
}