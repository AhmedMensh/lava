package com.android.pharous.app.lava.models


import com.squareup.moshi.Json

data class BodyBuildingRequest(
    @field:Json(name = "AccessToken")
    var accessToken: String? = null,
    @field:Json(name = "Calories")
    var calories: String? = null,
    @field:Json(name = "Duration")
    var duration: String? = null,
    @field:Json(name = "EquipmentID")
    var equipmentID: String? = null,
    @field:Json(name = "NumberOfRepetition")
    var numberOfRepetition: String? = null,
    @field:Json(name = "ProgramID")
    var programID: String? = null,
    @field:Json(name = "Repetition")
    var repetition: String? = null
)