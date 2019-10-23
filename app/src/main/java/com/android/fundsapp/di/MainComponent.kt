package com.android.fundsapp.di

import com.android.fundsapp.MainActivity
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

    fun inject(mainActivity: MainActivity)
}