package com.android.pharous.app.lava.models

import com.squareup.moshi.Json


data class VerificationCodeRequest(
    @field:Json(name = "MobileNumber")
    var mobileNumber: String? = null,
    @field:Json(name = "AccessToken")
    var accessToken: String? = null,
    @field:Json(name = "VerificationCode")
    var verificationCode: String? = null
)