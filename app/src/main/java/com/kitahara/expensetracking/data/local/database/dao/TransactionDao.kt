package com.kitahara.expensetracking.data.local.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kitahara.expensetracking.data.local.entity.TransactionEntity

@Dao
interface TransactionDao {

    @Upsert
    fun addTransaction(vararg transactionEntity: TransactionEntity)

    @Query("SELECT * from transaction_table t ORDER BY t.operation_date DESC")
    fun getAllDataPaged(): PagingSource<Int, TransactionEntity>
}