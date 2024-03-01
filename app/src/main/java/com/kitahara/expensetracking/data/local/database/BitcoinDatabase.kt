package com.kitahara.expensetracking.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kitahara.expensetracking.data.local.database.converter.DateConverter
import com.kitahara.expensetracking.data.local.database.dao.BitcoinRateDao
import com.kitahara.expensetracking.data.local.database.dao.TransactionDao
import com.kitahara.expensetracking.data.local.database.dao.UserBalanceDao
import com.kitahara.expensetracking.data.local.entity.BitcoinRateEntity
import com.kitahara.expensetracking.data.local.entity.TransactionEntity
import com.kitahara.expensetracking.data.local.entity.UserBalanceEntity

@Database(
    entities = [UserBalanceEntity::class, BitcoinRateEntity::class, TransactionEntity::class],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class BitcoinDatabase : RoomDatabase() {

    abstract fun userBalanceDao(): UserBalanceDao
    abstract fun bitcoinRateDao(): BitcoinRateDao
    abstract fun transactionDao(): TransactionDao

}