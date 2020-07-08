package com.android.pharous.app.lava.models

import com.squareup.moshi.Json

data class Result(
    @field:Json(name = "Result")
    var result: String? = null
)