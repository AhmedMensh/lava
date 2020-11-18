package com.android.pharous.app.lava.models


import com.squareup.moshi.Json

data class InbodyResultRequest(
    @field:Json(name = "AccessToken")
    var accessToken: String? = null,
    @field:Json(name = "ResultID")
    var resultID: String? = null,
    @field:Json(name = "BasalMetabolicRate")
    var basalMetabolicRate: String? = null,
    @field:Json(name = "BodyFatMass")
    var bodyFatMass: String? = null,
    @field:Json(name = "BodyFatPercent")
    var bodyFatPercent: String? = null,
    @field:Json(name = "FatControl")
    var fatControl: String? = null,
    @field:Json(name = "MuscleControl")
    var muscleControl: String? = null,
    @field:Json(name = "SMM")
    var sMM: String? = null,
    @field:Json(name = "TargetWeight")
    var targetWeight: String? = null,
    @field:Json(name = "TotalBodyWater")
    var totalBodyWater: String? = null,
    @field:Json(name = "VisceralFatLevel")
    var visceralFatLevel: String? = null,
    @field:Json(name = "WaistHipRatio")
    var waistHipRatio: String? = null,
    @field:Json(name = "Weight")
    var weight: String? = null,
    @field:Json(name = "WeightControl")
    var weightControl: String? = null
)