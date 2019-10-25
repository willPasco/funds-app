package com.android.fundsapp.di

import com.android.fundsapp.core.BasePresenter
import com.android.fundsapp.core.BasePresenterImpl
import com.android.fundsapp.core.BaseView
import com.android.fundsapp.data.repository.FundsRepository
import com.android.fundsapp.domain.FundsListView
import com.android.fundsapp.domain.presenter.FundsListPresenter
import com.android.fundsapp.domain.presenter.FundsListPresenterImpl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class PresenterModule {

    @Provides
    fun provideBasePresenter(compositeDisposable: CompositeDisposable): BasePresenter {
        return BasePresenterImpl(compositeDisposable = compositeDisposable)
    }

    @Provides
    fun provideFundsListPresenter(compositeDisposable: CompositeDisposable, repository: FundsRepository ): FundsListPresenter {
        return FundsListPresenterImpl(repository = repository,  compositeDisposable = compositeDisposable
        )
    }

}