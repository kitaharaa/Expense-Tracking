package com.kitahara.expensetracking.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(): ViewModel() {
    fun addBitcoin(it: Float) {
        //todo implement
    }

    //TODO change
    fun provideBitcoinFlow(): Flow<Float> = flowOf(0.233f)

}