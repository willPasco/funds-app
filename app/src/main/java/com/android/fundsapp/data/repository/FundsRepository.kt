package com.android.fundsapp.fundslist

import android.annotation.SuppressLint
import android.util.Log
import com.android.fundsapp.data.service.FundsService
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FundsRepository @Inject constructor(private val service: FundsService) {

    @SuppressLint("CheckResult")
    fun getFunds() {
        service.getFunds("100", "0", "fund_detail_full")
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { response ->
                    response.forEach {
                        Log.i("test", "${it.simpleName} ${it.operability.minimumApplication}")
                    }
                },
                { error -> Log.e("test", error.toString()) }
            )
    }
}