package com.kitahara.expensetracking.di.modules

import android.content.Context
import androidx.room.Room
import com.kitahara.expensetracking.data.local.database.BitcoinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        BitcoinDatabase::class.java,
        "BitcoinDatabase"
    ).build()

    @Singleton
    @Provides
    fun provideUserBalanceDao(database: BitcoinDatabase) = database.userBalanceDao()

    @Singleton
    @Provides
    fun provideBitcoinRateDao(database: BitcoinDatabase) = database.bitcoinRateDao()

    @Singleton
    @Provides
    fun provideTransactionDao(database: BitcoinDatabase) = database.transactionDao()

}