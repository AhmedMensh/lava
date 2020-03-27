package com.android.pharous.app.lava.models


import com.squareup.moshi.Json

data class ProfileResponse(
    @field:Json(name = "Address")
    var address: String? = null,
    @field:Json(name = "BirthDate")
    var birthDate: String? = null,
    @field:Json(name = "CityID")
    var cityID: Int? = null,
    @field:Json(name = "CityName")
    var cityName: String? = null,
    @field:Json(name = "Email")
    var email: String? = null,
    @field:Json(name = "FullName")
    var fullName: String? = null,
    @field:Json(name = "IdentityID")
    var identityID: Int? = null,
    @field:Json(name = "JobTitleID")
    var jobTitleID: Int? = null,
    @field:Json(name = "JobTitleName")
    var jobTitleName: String? = null,
    @field:Json(name = "Language")
    var language: String? = null,
    @field:Json(name = "MobileNumber")
    var mobileNumber: Long? = null,
    @field:Json(name = "NationalityID")
    var nationalityID: Int? = null,
    @field:Json(name = "NationalityName")
    var nationalityName: String? = null,
    @field:Json(name = "RegionID")
    var regionID: Int? = null,
    @field:Json(name = "RegionName")
    var regionName: String? = null,
    @field:Json(name = "SocialStatus")
    var socialStatus: Int? = null
)