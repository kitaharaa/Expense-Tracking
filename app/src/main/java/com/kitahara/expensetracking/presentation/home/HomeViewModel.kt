package com.kitahara.expensetracking.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kitahara.expensetracking.domain.BitcoinOperationUseCase
import com.kitahara.expensetracking.domain.entity.TransactionData
import com.kitahara.expensetracking.domain.repo.BalanceSource
import com.kitahara.expensetracking.domain.repo.BitcoinRateSource
import com.kitahara.expensetracking.domain.repo.MyPagingData
import com.kitahara.expensetracking.domain.sources.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val balanceSource: BalanceSource,
    private val bitcoinOperationUseCase: BitcoinOperationUseCase,
    private val myPagingData: MyPagingData,
    private val bitcoinRateSource: BitcoinRateSource
) : ViewModel() {

    fun getPagedTransactions(): Flow<PagingData<TransactionData>> = myPagingData
        .getPagingTransactions()
        .cachedIn(viewModelScope)

    fun addBitcoin(it: String, showToast: (String) -> Unit) {
        viewModelScope.launch(IO) {
            bitcoinOperationUseCase.createTransaction(
                transactionType = TransactionType.Replenishment,
                amount = it,
                category = "replenishment",
                showToast= showToast
            )
        }
    }

    fun provideBitcoinFlow(): Flow<Float> = balanceSource.getBitcoinAmountFlow()

    fun provideBitcoinRateFlow() = bitcoinRateSource.getBitcoinToUsdRateFlow()

    fun updateBitcoinRate() {
        viewModelScope.launch(IO) {
            bitcoinRateSource.updateBitcoinRate()
        }
    }
}