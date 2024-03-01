package com.kitahara.expensetracking.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kitahara.expensetracking.domain.BalanceSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val balanceSource: BalanceSource
) : ViewModel() {
    fun addBitcoin(it: Float) {
        viewModelScope.launch(IO) { balanceSource.replenishBalance(it) }
    }

    fun provideBitcoinFlow(): Flow<Float> = balanceSource.getBitcoinAmountFlow()

}