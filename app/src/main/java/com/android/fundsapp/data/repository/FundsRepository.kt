package com.android.fundsapp.data.repository

import android.annotation.SuppressLint
import com.android.fundsapp.data.entity.FundResponse
import com.android.fundsapp.data.service.FundsService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FundsRepository @Inject constructor(private val service: FundsService) {

    @SuppressLint("CheckResult")
    fun getFunds(): Single<List<FundResponse>> {
        return service.getFunds("100", "0", "fund_detail_full")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}