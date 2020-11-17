package com.android.pharous.app.lava.ui.measurement


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MemberInbodyresultResponse(
    @field:Json(name = "BasalMetabolicRate")
    var basalMetabolicRate: Int? = null,
    @field:Json(name = "BodyFatMass")
    var bodyFatMass: Int? = null,
    @field:Json(name = "FatControl")
    var fatControl: Int? = null,
    @field:Json(name = "VisceralFatLevel")
    var visceralFatLevel: Int? = null,
    @field:Json(name = "Buttocks")
    var buttocks: Int? = null,
    @field:Json(name = "CreationDate")
    var creationDate: String? = null,
    @field:Json(name = "DryLeanMass")
    var dryLeanMass: Int? = null,
    @field:Json(name = "ID")
    var iD: Int? = null,
    @field:Json(name = "MusleFatMass")
    var musleFatMass: Int? = null,
    @field:Json(name = "MuscleControl")
    var muscleControl: Int? = null,
    @field:Json(name = "PBF")
    var pBF: Int? = null,
    @field:Json(name = "SMM")
    var sMM: Int? = null,
    @field:Json(name = "Arm")
    var arm: Int? = null,
    @field:Json(name = "WaistHipRatio")
    var waistHipRatio: Int? = null,
    @field:Json(name = "Thigh")
    var thigh: Int? = null,
    @field:Json(name = "Calf")
    var calf: Int? = null,
    @field:Json(name = "Chest")
    var chest: Int? = null,
    @field:Json(name = "TotalBodyWater")
    var totalBodyWater: Int? = null,
    @field:Json(name = "Abdominal")
    var abdominal: Int? = null,
    @field:Json(name = "Waist")
    var waist: Int? = null,
    @field:Json(name = "Weight")
    var weight: Int? = null,
    @field:Json(name = "WeightControl")
    var weightControl: Int? = null,
    @field:Json(name = "TargetWeight")
    var targetWeight: Int? = null
) : Parcelable
