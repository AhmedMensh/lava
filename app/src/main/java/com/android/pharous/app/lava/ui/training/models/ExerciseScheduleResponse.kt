package com.android.pharous.app.lava.ui.training.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class ExerciseScheduleResponse(
    @field:Json(name = "BranchID")
    var branchID: String? = null,
    @field:Json(name = "BranchName")
    var branchName: String? = null,
    @field:Json(name = "Capacity")
    var capacity: String? = null,
    @field:Json(name = "CoachID")
    var coachID: String? = null,
    @field:Json(name = "CoachName")
    var coachName: String? = null,
    @field:Json(name = "Date")
    var date: String? = null,
    @field:Json(name = "Duration")
    var duration: String? = null,
    @field:Json(name = "ExerciseID")
    var exerciseID: String? = null,
    @field:Json(name = "ExerciseTitle")
    var exerciseTitle: String? = null,
    @field:Json(name = "ID")
    var iD: String? = null,
    @field:Json(name = "ReservationCount")
    var reservationCount: String? = null,
    @field:Json(name = "Type")
    var type: String? = null
)