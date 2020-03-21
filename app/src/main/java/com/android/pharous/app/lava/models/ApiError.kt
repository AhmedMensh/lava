package com.android.pharous.app.lava.models

import com.squareup.moshi.Json

data class ApiError(
    @field:Json(name = "errors")
    val errors: String?,
    @field:Json(name = "error_code")
    val error_code: Int?,
    @field:Json(name = "status")
    val status: Int?
)
