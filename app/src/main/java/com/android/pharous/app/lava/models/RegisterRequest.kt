package com.android.pharous.app.lava.models


import com.squareup.moshi.Json

data class RegisterRequest(
    @field:Json(name = "BirthDate")
    var birthDate: String? = null,
    @field:Json(name = "CityID")
    var cityID: String? = null,
    @field:Json(name = "Email")
    var email: String? = null,
    @field:Json(name = "FullName")
    var fullName: String? = null,
    @field:Json(name = "Language")
    var language: String? = null,
    @field:Json(name = "MobileNumber")
    var mobileNumber: String? = null,
    @field:Json(name = "NationalityID")
    var nationalityID: String? = null,
    @field:Json(name = "RegionID")
    var regionID: String? = null
)