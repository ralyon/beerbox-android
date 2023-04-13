package com.ralyon.data.model

import com.google.gson.annotations.SerializedName
import com.ralyon.data.model.Amount

data class Malt(
    @SerializedName("amount")
    val amount: Amount = Amount(),
    @SerializedName("name")
    val name: String = ""
)