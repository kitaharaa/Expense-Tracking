package com.kitahara.expensetracking.domain

import com.kitahara.expensetracking.domain.repo.BalanceSource
import com.kitahara.expensetracking.domain.repo.TransactionSource
import com.kitahara.expensetracking.domain.sources.TransactionType
import javax.inject.Inject

class BitcoinOperationUseCase @Inject constructor(
    private val balanceSource: BalanceSource,
    private val transactionSource: TransactionSource
) {

    suspend fun createTransaction(
        transactionType: TransactionType,
        amount: String,
        category: String?,
        showToast: (String) -> Unit,
        onComplete: (() -> Unit)? = null
    ) {
        try {
            val bitcoinBalance = balanceSource.getBitcoinBalanceAmount()
            var parsedAmount = makeAmountValid(amount)

            if (transactionType == TransactionType.Expense)
                parsedAmount *= -1

            if (bitcoinBalance + parsedAmount < 0)
                throw Exception("Not enough money")

            transactionSource.saveTransaction(category, parsedAmount)

            balanceSource.addCost(parsedAmount)

            onComplete?.invoke()
        } catch (e: Exception) {
            showToast(e.message.toString())
        }
    }

    private fun makeAmountValid(amount: String): Float =
        amount.toFloatOrNull() ?: throw Exception("Amount is not valid")
}