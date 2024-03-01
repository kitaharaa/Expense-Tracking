package com.kitahara.expensetracking.di.modules.sources

import com.kitahara.expensetracking.domain.repo.BalanceSource
import com.kitahara.expensetracking.domain.repo.BitcoinRateSource
import com.kitahara.expensetracking.domain.repo.MyPagingData
import com.kitahara.expensetracking.domain.repo.TransactionSource
import com.kitahara.expensetracking.domain.sources.BalanceSourceImpl
import com.kitahara.expensetracking.domain.sources.MyPagingDataImpl
import com.kitahara.expensetracking.domain.sources.BitcoinRateSourceImpl
import com.kitahara.expensetracking.domain.sources.TransactionSourceImpl
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

    @Binds
    abstract fun bindTransactionSource(impl: TransactionSourceImpl): TransactionSource
}