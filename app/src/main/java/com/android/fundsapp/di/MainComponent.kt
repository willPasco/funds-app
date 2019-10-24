package com.android.fundsapp.di

import com.android.fundsapp.fundslist.FundsListActivity
import com.android.fundsapp.core.BaseActivity
import com.android.fundsapp.core.BasePresenter
import com.android.fundsapp.domain.presenter.FundsListPresenter
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [NetworkModule::class,
        PresenterModule::class,
        RepositoryModule::class,
        ModelModule::class]
)
@Singleton
interface MainComponent {

    fun inject(baseActivity: BaseActivity)
    fun inject(mainActivity: FundsListActivity)

    fun inject(basePresenter: BasePresenter)
    fun inject(fundsListPresenter: FundsListPresenter)
}