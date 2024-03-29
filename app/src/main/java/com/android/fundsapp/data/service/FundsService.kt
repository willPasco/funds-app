package com.android.fundsapp.data.service

import com.android.fundsapp.data.entity.FundResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FundsService {

    @GET("/orama-media/json/fund_detail_full.json")
    fun getFunds(
        @Query("limit") limit: String,
        @Query("offset") offset: String,
        @Query("serialize") serializer: String
    ): Single<List<FundResponse>>
}