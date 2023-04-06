package com.ralyon.data.model

import com.google.gson.annotations.SerializedName
import com.ralyon.data.model.Amount

data class MashTemp(
    @SerializedName("duration")
    val duration: Int = 0,
    @SerializedName("temp")
    val temp: Amount = Amount()
)