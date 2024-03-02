package com.kitahara.expensetracking.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("bitcoin_balance_table")
data class UserBalanceEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    @ColumnInfo("bitcoin_amount")
    val bitcoinAmount: Float,
)