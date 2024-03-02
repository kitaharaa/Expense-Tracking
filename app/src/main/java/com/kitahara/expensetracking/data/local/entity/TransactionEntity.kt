package com.kitahara.expensetracking.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("transaction_table")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("operation_date")
    val operationDate: Date,
    @ColumnInfo("spend_amount")
    val spendAmount: Float,
    val category: String
)