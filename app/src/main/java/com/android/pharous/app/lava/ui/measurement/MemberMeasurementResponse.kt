package com.android.pharous.app.lava.ui.measurement


import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class MemberMeasurementResponse(
    @field:Json(name = "Abdominal")
    var abdominal: Int? = null,
    @field:Json(name = "Arm")
    var arm: Int? = null,
    @field:Json(name = "Buttocks")
    var buttocks: Int? = null,
    @field:Json(name = "Calf")
    var calf: Int? = null,
    @field:Json(name = "Chest")
    var chest: Int? = null,
    @field:Json(name = "CreationDate")
    var creationDate: String? = null,
    @field:Json(name = "ID")
    var iD: Int? = null,
    @field:Json(name = "Length")
    var length: Int? = null,
    @field:Json(name = "Thigh")
    var thigh: Int? = null,
    @field:Json(name = "Waist")
    var waist: Int? = null,
    @field:Json(name = "Weight")
    var weight: Int? = null
)