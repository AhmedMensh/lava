package com.android.pharous.app.lava.models


import com.squareup.moshi.Json

data class AmtarContractResponse(
    @Json(name = "AccessToken")
    var accessToken: String? = null,
    @Json(name = "BranchID")
    var branchID: String? = null,
    @Json(name = "ContractID")
    var contractID: Int? = null,
    @Json(name = "StartDate")
    var startDate: String? = null
)