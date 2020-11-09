package com.android.pharous.app.lava.models


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PointHistoryResponse(
    @field:Json(name = "CreationDate")
    var creationDate: String? = null,
    @field:Json(name = "Event")
    var event: String? = null,
    @field:Json(name = "NumberOfPoint")
    var numberOfPoint: Int? = null
) : Parcelable