package com.kitahara.expensetracking.domain

import com.kitahara.expensetracking.domain.repo.BalanceSource
import com.kitahara.expensetracking.domain.repo.TransactionSource
import com.kitahara.expensetracking.domain.sources.TransactionType
import javax.inject.Inject

//Use case for handling transaction saving. It can be replenishment/expense
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
            var parsedAmount =
                makeAmountValid(amount) //check if state of TextField is possible to convert in float

            if (transactionType == TransactionType.Expense) //here we determine whether to add float or subtract it
                parsedAmount *= -1

            if (bitcoinBalance + parsedAmount < 0) //if possible to make operation with available bitcoin amount
                throw Exception("Not enough money")

            transactionSource.saveTransaction(category, parsedAmount) //save transaction

            balanceSource.addCost(parsedAmount) //update balance

            onComplete?.invoke()
        } catch (e: Exception) {
            showToast(e.message.toString())
        }
    }

    private fun makeAmountValid(amount: String): Float =
        amount.toFloatOrNull() ?: throw Exception("Amount is not valid")
}