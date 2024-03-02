package com.kitahara.expensetracking.domain.sources

import android.util.Log
import com.kitahara.expensetracking.data.local.database.dao.UserBalanceDao
import com.kitahara.expensetracking.data.local.entity.UserBalanceEntity
import com.kitahara.expensetracking.domain.repo.BalanceSource
import javax.inject.Inject

class BalanceSourceImpl @Inject constructor(
    private val balanceDao: UserBalanceDao
) : BalanceSource {

    override fun getBitcoinAmountFlow() = balanceDao.getBitcoinAmountFlow()

    override suspend fun addCost(sum: Float) {
        try {
            val amount = balanceDao.getBitcoinAmount()
            balanceDao.upsert(UserBalanceEntity(bitcoinAmount = amount + sum))
        } catch (e: Exception) {
            Log.e("BalanceSource", e.message.toString())
        }
    }

    override suspend fun getBitcoinBalanceAmount(): Float {
        return try {
            balanceDao.getBitcoinAmount()
        } catch (_: Exception) {
            0f
        }
    }
}