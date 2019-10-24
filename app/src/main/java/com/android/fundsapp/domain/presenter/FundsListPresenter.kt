package com.android.fundsapp.domain.presenter

import com.android.fundsapp.core.BasePresenter
import com.android.fundsapp.domain.FundsListView

interface FundsListPresenter : BasePresenter {

    fun getFunds()
    fun bind(view: FundsListView)
}