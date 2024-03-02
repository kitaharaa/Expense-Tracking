package com.kitahara.expensetracking.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("bitcoin_course_table")
data class BitcoinRateEntity(
    @PrimaryKey(false)
    val id: Int = 0,
    @ColumnInfo("last_course_update")
    val lastCourseUpdate: Date,
    @ColumnInfo("usd_rate")
    val usdRate: Float
)