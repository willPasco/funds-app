package com.android.fundsapp.domain

import com.android.fundsapp.core.BaseView
import com.android.fundsapp.data.entity.FundResponse

interface FundsListView : BaseView {
    fun showData(dataList: List<FundResponse>)
    fun showError()
}