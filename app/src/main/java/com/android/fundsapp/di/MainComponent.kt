package com.android.fundsapp.di

import com.android.fundsapp.MainActivity
import dagger.Component

@Component(
    modules = [NetworkModule::class,
        PresenterModule::class,
        RepositoryModule::class,
        ModelModule::class]
)
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}