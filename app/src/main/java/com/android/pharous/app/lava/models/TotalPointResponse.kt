package com.android.pharous.app.lava.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class TotalPointResponse(
    @field:Json(name = "CurrentLevel")
    var currentLevel: String? = null,
    @field:Json(name = "TotalPoint")
    var totalPoint: String? = null
)