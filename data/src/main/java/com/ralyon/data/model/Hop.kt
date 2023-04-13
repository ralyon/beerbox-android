package com.ralyon.data.model

import com.google.gson.annotations.SerializedName
import com.ralyon.data.model.Amount

data class Hop(
    @SerializedName("add")
    val add: String = "",
    @SerializedName("amount")
    val amount: Amount = Amount(),
    @SerializedName("attribute")
    val attribute: String = "",
    @SerializedName("name")
    val name: String = ""
)