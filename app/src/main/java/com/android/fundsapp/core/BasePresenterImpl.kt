package com.android.fundsapp.core

import com.android.fundsapp.AppApplication
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BasePresenterImpl @Inject constructor(val compositeDisposable: CompositeDisposable) :
    BasePresenter {

    private lateinit var view: BaseView

    override fun bind(view: BaseView) {
        this.view = view
    }

    init {
        AppApplication.getMainComponent().inject(this)
    }

    override fun onStop() {
        compositeDisposable.clear()
    }
}