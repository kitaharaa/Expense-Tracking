package com.kitahara.expensetracking.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kitahara.expensetracking.data.local.entity.BitcoinRateEntity
import java.util.Date
import kotlinx.coroutines.flow.Flow

@Dao
interface BitcoinRateDao {

    @Upsert
    fun upsert(bce: BitcoinRateEntity)

    @Query("SELECT b.last_course_update FROM bitcoin_course_table b")
    fun getLastUpdateTime(): Date

    @Query("SELECT b.usd_rate FROM bitcoin_course_table b")
    fun getBitcoinToUsdRate(): Flow<Float>
}