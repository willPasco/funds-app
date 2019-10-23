package com.android.fundsapp.domain.presenter

import android.util.Log
import com.android.fundsapp.AppApplication
import com.android.fundsapp.core.BasePresenterImpl
import com.android.fundsapp.data.repository.FundsRepository
import com.android.fundsapp.domain.FundsListView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FundsListPresenterImpl @Inject constructor(
    private val repository: FundsRepository,
    compositeDisposable: CompositeDisposable) : BasePresenterImpl(compositeDisposable), FundsListPresenter {

    lateinit var view: FundsListView

    init {
        AppApplication.getMainComponent().inject(this@FundsListPresenterImpl)
    }

    override fun bind(view: FundsListView) {
        this.view = view
    }

    override fun getFunds() {
        compositeDisposable.add(
            repository.getFunds()
                .subscribe(
                    { response ->
                        response.forEach {
                            Log.i("test", "${it.simpleName} ${it.operability.minimumApplication}")
                        }
                    },
                    { error -> Log.e("test", error.toString()) }
                ))
    }
}