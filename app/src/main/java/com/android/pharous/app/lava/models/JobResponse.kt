package com.android.pharous.app.lava.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class JobResponse(
    @field:Json(name = "ID")
    var iD: Int? = null,
    @field:Json(name = "TitleAR")
    var titleAR: String? = null,
    @field:Json(name = "TitleEN")
    var titleEN: String? = null
)