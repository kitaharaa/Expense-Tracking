package com.kitahara.expensetracking.presentation.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kitahara.expensetracking.domain.BitcoinOperationUseCase
import com.kitahara.expensetracking.domain.sources.TransactionType
import com.kitahara.expensetracking.presentation.transaction.transaction_categories.TransactionCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val bitcoinOperationUseCase: BitcoinOperationUseCase
) : ViewModel() {

    fun saveTransaction(
        transactionSum: String,
        clickedCategory: TransactionCategory?,
        onComplete: () -> Unit,
        showToast: (String) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            bitcoinOperationUseCase.createTransaction(
                transactionType = TransactionType.Expense,
                amount = transactionSum,
                category = clickedCategory?.category,
                showToast = showToast,
                onComplete = onComplete
            )
        }
    }
}