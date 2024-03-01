package com.kitahara.expensetracking.di.modules.sources

import com.kitahara.expensetracking.domain.BalanceSource
import com.kitahara.expensetracking.domain.balance.BalanceSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SourcesModule {
    @Binds
    abstract fun bindBalanceSource(bs: BalanceSourceImpl): BalanceSource
}