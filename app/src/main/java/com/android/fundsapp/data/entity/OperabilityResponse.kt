package com.android.fundsapp.data.entity

import com.google.gson.annotations.SerializedName

data class OperabilityResponse(
    @SerializedName("minimum_initial_application_amount")
    var minimumApplication:Double)
