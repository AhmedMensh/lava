package com.android.pharous.app.lava.ui.workout.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class EvaluateProgramRequest(
    @field:Json(name = "AccessToken")
    var accessToken: String? = null,
    @field:Json(name = "Level")
    var level: String? = null,
    @field:Json(name = "ProgramID")
    var programID: String? = null
)