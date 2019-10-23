package com.android.fundsapp

import android.app.Application
import com.android.fundsapp.di.DaggerMainComponent
import com.android.fundsapp.di.MainComponent

class AppApplication : Application() {

    private lateinit var daggerMainComponent: MainComponent

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        daggerMainComponent = DaggerMainComponent.builder().build()
    }

    fun getMainComponent(): MainComponent {
        return daggerMainComponent
    }
}