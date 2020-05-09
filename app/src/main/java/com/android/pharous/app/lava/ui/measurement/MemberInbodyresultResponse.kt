package com.android.pharous.app.lava.ui.measurement


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class MemberInbodyresultResponse(
    @field:Json(name = "BasalMetabolicRate")
    var basalMetabolicRate: Int? = null,
    @field:Json(name = "BodyFatMass")
    var bodyFatMass: Int? = null,
    @field:Json(name = "CreationDate")
    var creationDate: String? = null,
    @field:Json(name = "DryLeanMass")
    var dryLeanMass: Int? = null,
    @field:Json(name = "ID")
    var iD: Int? = null,
    @field:Json(name = "MusleFatMass")
    var musleFatMass: Int? = null,
    @field:Json(name = "PBF")
    var pBF: Int? = null,
    @field:Json(name = "SMM")
    var sMM: Int? = null,
    @field:Json(name = "TotalBodyWater")
    var totalBodyWater: Int? = null,
    @field:Json(name = "Weight")
    var weight: Int? = null
)