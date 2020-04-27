package com.android.pharous.app.lava.models


import com.squareup.moshi.Json

data class BranchResponse(
    @field:Json(name = "Address")
    var address: String? = null,
    @field:Json(name = "AddressEN")
    var addressEN: Any? = null,
    @field:Json(name = "ID")
    var iD: Int? = null,
    @field:Json(name = "NameAR")
    var nameAR: String? = null,
    @field:Json(name = "NameEN")
    var nameEN: String? = null,
    @field:Json(name = "Phone")
    var phone: Any? = null,
    @field:Json(name = "RegionID")
    var regionID: Int? = null
)