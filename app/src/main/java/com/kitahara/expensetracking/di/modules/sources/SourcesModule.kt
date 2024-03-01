package com.kitahara.expensetracking.di.modules.sources

import com.kitahara.expensetracking.domain.BalanceSource
import com.kitahara.expensetracking.domain.BitcoinRateSource
import com.kitahara.expensetracking.domain.MyPagingData
import com.kitahara.expensetracking.domain.balance.BalanceSourceImpl
import com.kitahara.expensetracking.domain.paging.MyPagingDataImpl
import com.kitahara.expensetracking.domain.rate.BitcoinRateSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SourcesModule {
    @Binds
    abstract fun bindBalanceSource(impl: BalanceSourceImpl): BalanceSource

    @Binds
    abstract fun bindMyPagingData(impl: MyPagingDataImpl): MyPagingData

    @Binds
    abstract fun bindBitcoinRateSource(impl: BitcoinRateSourceImpl): BitcoinRateSource
}