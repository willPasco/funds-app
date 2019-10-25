package com.android.fundsapp.data.entity

import com.google.gson.annotations.SerializedName

data class FundResponse(
    @SerializedName("simple_name")
    var simpleName: String,
    var operability: OperabilityResponse )