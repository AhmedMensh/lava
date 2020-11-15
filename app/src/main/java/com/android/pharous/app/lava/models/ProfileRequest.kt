package com.android.pharous.app.lava.models


import com.squareup.moshi.Json

data class ProfileRequest(
    @field:Json(name = "AccessToken")
    var accessToken: String? = null,
    @field:Json(name = "Address")
    var address: String? = null,
    @field:Json(name = "BirthDate")
    var birthDate: String? = null,
    @field:Json(name = "BranchID")
    var branchID: String? = null,
    @field:Json(name = "CityID")
    var cityID: String? = null,
    @field:Json(name = "Email")
    var email: String? = null,
    @field:Json(name = "EmergencyPhone")
    var emergencyPhone: String? = null,
    @field:Json(name = "FullName")
    var fullName: String? = null,
    @field:Json(name = "Goal")
    var goal: String? = null,
    @field:Json(name = "IdentityID")
    var identityID: String? = null,
    @field:Json(name = "JobTitleID")
    var jobTitleID: String? = null,
    @field:Json(name = "Language")
    var language: String? = null,
    @field:Json(name = "MobileNumber")
    var mobileNumber: String? = null,
    @field:Json(name = "NationalityID")
    var nationalityID: String? = null,
    @field:Json(name = "RegionID")
    var regionID: String? = null,
    @field:Json(name = "SocialStatus")
    var socialStatus: String? = null
)