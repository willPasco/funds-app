package com.android.fundsapp.core

interface BasePresenter {

    fun onStop()
    fun bind(view: BaseView)
}