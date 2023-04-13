package com.ralyon.data.model

import com.google.gson.annotations.SerializedName

data class Beer(
    @SerializedName("abv")
    val abv: Double = 0.0,
    @SerializedName("attenuation_level")
    val attenuationLevel: Double = 0.0,
    @SerializedName("boil_volume")
    val boilVolume: Amount = Amount(),
    @SerializedName("brewers_tips")
    val brewersTips: String = "",
    @SerializedName("contributed_by")
    val contributedBy: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("ebc")
    val ebc: Double = 0.0,
    @SerializedName("first_brewed")
    val firstBrewed: String = "",
    @SerializedName("food_pairing")
    val foodPairing: List<String> = emptyList(),
    @SerializedName("ibu")
    val ibu: Double = 0.0,
    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("image_url")
    val imageUrl: String = "",
    @SerializedName("ingredients")
    val ingredients: Ingredients = Ingredients(),
    @SerializedName("method")
    val method: Method = Method(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("ph")
    val ph: Double = 0.0,
    @SerializedName("srm")
    val srm: Double = 0.0,
    @SerializedName("tagline")
    val tagline: String = "",
    @SerializedName("target_fg")
    val targetFg: Double = 0.0,
    @SerializedName("target_og")
    val targetOg: Double = 0.0,
    @SerializedName("volume")
    val volume: Amount = Amount()
)