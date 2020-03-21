package com.android.pharous.app.lava.models

import com.squareup.moshi.Json

data class ApiResponse<T>(
    @field:Json(name = "data")
    val data: T?,
    @field:Json(name = "status")
    val status: Int?
)
