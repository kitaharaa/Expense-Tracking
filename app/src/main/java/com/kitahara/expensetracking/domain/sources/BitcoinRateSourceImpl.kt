package com.kitahara.expensetracking.domain.sources

import android.util.Log
import com.kitahara.expensetracking.data.local.database.dao.BitcoinRateDao
import com.kitahara.expensetracking.data.local.entity.BitcoinRateEntity
import com.kitahara.expensetracking.data.request.BitcoinDataSource
import com.kitahara.expensetracking.domain.repo.BitcoinRateSource
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class BitcoinRateSourceImpl @Inject constructor(
    private val rateDao: BitcoinRateDao,
    private val bitcoinDataSource: BitcoinDataSource
) : BitcoinRateSource {

    override suspend fun updateBitcoinRate() {
        try {
            val hours = getLastUpdateTimeInHours(rateDao.getLastUpdateTime())
            if (hours < 1) return

            val bitcoinInfo = bitcoinDataSource.getBitcoinInfo()
            val bitcoinUpdateTime = bitcoinInfo?.time?.updatedISO
            val bitcoinUsdRate = bitcoinInfo?.bpi?.uSD?.rateFloat

            val date = bitcoinUpdateTime.convertToDate()

            if (date != null && bitcoinUsdRate != null)
                rateDao.upsert(
                    BitcoinRateEntity(
                        lastCourseUpdate = date,
                        usdRate = bitcoinUsdRate
                    )
                )
        } catch (e: Exception) {
            Log.e("BitcoinRateSourceImpl", e.message.toString())
        }
    }

    override fun getBitcoinToUsdRateFlow(): Flow<Float> = rateDao.getBitcoinToUsdRateFlow()

    private fun String?.convertToDate(): Date? {
        if (this == null) throw Exception("Iso time is null")

        return SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ssXXX",
            Locale.ENGLISH
        ).parse(this)
    }

    private fun getLastUpdateTimeInHours(date: Date?): Long {
        if (date == null) return 1

        val currentTime = Date()
        val diffInMillis = currentTime.time - date.time

        return TimeUnit.MILLISECONDS.toHours(diffInMillis)
    }
}
