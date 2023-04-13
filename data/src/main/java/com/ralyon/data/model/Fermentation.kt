package com.ralyon.data.model

import com.google.gson.annotations.SerializedName
import com.ralyon.data.model.Amount

data class Fermentation(
    @SerializedName("temp")
    val temp: Amount = Amount()
)