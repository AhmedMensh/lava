package com.android.pharous.app.lava.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class RegionResponse(
    @field:Json(name = "ID")
    var iD: Int? = null,
    @field:Json(name = "CityID")
    var cityID: Int? = null,
    @field:Json(name = "NameAR")
    var nameAR: String? = null,
    @field:Json(name = "NameEN")
    var nameEN: String? = null
)