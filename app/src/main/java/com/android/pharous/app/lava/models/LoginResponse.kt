package com.android.pharous.app.lava.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class LoginResponse(
    @field:Json(name = "AccessToken")
    val accessToken: String? = null,
    @field:Json(name = "VerificationCode")
    val verificationCode: Int? = null
)