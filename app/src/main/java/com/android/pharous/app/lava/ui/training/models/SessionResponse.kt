package com.android.pharous.app.lava.ui.training.models


import com.squareup.moshi.Json

data class SessionResponse(
    @field:Json(name = "BranchName")
    var branchName: String? = null,
    @field:Json(name = "Date")
    var date: String? = null,
    @field:Json(name = "ID")
    var iD: String? = null,
    @field:Json(name = "IsAttended")
    var isAttended: String? = null,
    @field:Json(name = "ServiceName")
    var serviceName: String? = null,
    @field:Json(name = "Time")
    var time: String? = null,
    @field:Json(name = "TrainerName")
    var trainerName: String? = null
)