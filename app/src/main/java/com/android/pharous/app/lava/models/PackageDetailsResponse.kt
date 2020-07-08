package com.android.pharous.app.lava.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class PackageDetailsResponse(
    @field:Json(name = "MaximumStartingDate")
    var maximumStartingDate: Int? = null,
    @field:Json(name = "NumberOfPoint")
    var numberOfPoint: Int? = null,
    @field:Json(name = "Period")
    var period: Int? = null,
    @field:Json(name = "Services")
    var services: Map<String,String>? = null
)