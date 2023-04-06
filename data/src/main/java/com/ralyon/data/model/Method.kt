package com.ralyon.data.model

import com.google.gson.annotations.SerializedName
import com.ralyon.data.model.Fermentation
import com.ralyon.data.model.MashTemp

data class Method(
    @SerializedName("fermentation")
    val fermentation: Fermentation = Fermentation(),
    @SerializedName("mash_temp")
    val mashTemp: List<MashTemp> = emptyList(),
    @SerializedName("twist")
    val twist: String = ""
)