package com.android.pharous.app.lava.models

import com.squareup.moshi.Json


data class VerificationCodeRequest(
    @field:Json(name = "MobileNumber")
    var MobileNumber: String? = null,
    @field:Json(name = "AccessToken")
    var AccessToken: String? = null,
    @field:Json(name = "VerificationCode")
    var VerificationCode: Int? = null
)