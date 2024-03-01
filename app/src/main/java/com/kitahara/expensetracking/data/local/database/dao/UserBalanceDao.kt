package com.kitahara.expensetracking.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kitahara.expensetracking.data.local.entity.UserBalanceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserBalanceDao {

    @Upsert
    fun upsert(userBalanceEntity: UserBalanceEntity)

    @Query("SELECT b.bitcoin_amount FROM bitcoin_balance_table b WHERE id = 0")
    fun getBitcoinAmountFlow(): Flow<Float>

    @Query("SELECT b.bitcoin_amount FROM bitcoin_balance_table b WHERE id = 0")
    fun getBitcoinAmount(): Float

}