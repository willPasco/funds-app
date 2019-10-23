package com.android.fundsapp.di

import com.android.fundsapp.fundslist.FundsRepository
import com.android.fundsapp.data.service.FundsService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {


    @Provides
    fun providesFundsRepository(service: FundsService) = FundsRepository(service)
}