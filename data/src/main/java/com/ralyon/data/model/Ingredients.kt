package com.ralyon.data.model

import com.google.gson.annotations.SerializedName

data class Ingredients(
    @SerializedName("hops")
    val hops: List<Hop> = emptyList(),
    @SerializedName("malt")
    val malt: List<Malt> = emptyList(),
    @SerializedName("yeast")
    val yeast: String = ""
)