package com.kitahara.expensetracking.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kitahara.expensetracking.domain.BalanceSource
import com.kitahara.expensetracking.domain.BitcoinRateSource
import com.kitahara.expensetracking.domain.MyPagingData
import com.kitahara.expensetracking.domain.paging.entity.TransactionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val balanceSource: BalanceSource,
    private val myPagingData: MyPagingData,
    private val bitcoinRateSource: BitcoinRateSource
) : ViewModel() {

    fun getPagedTransactions(): Flow<PagingData<TransactionData>> = myPagingData
        .getPagingTransactions()
        .cachedIn(viewModelScope)

    fun addBitcoin(it: Float) {
        viewModelScope.launch(IO) { balanceSource.replenishBalance(it) }
    }

    fun provideBitcoinFlow(): Flow<Float> = balanceSource.getBitcoinAmountFlow()

    fun provideBitcoinRateFlow() = bitcoinRateSource.getBitcoinToUsdRateFlow()
}