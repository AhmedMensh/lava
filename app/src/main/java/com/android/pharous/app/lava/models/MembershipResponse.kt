package com.android.pharous.app.lava.models


import com.squareup.moshi.Json

data class MembershipResponse(
    @Json(name = "BranchID")
    var branchID: Int? = null,
    @Json(name = "BranchName")
    var branchName: String? = null,
    @Json(name = "CreationDate")
    var creationDate: String? = null,
    @Json(name = "EndDate")
    var endDate: String? = null,
    @Json(name = "Period")
    var period: Int? = null,
    @Json(name = "Services")
    var services: Services? = null,
    @Json(name = "StartDate")
    var startDate: String? = null
) {
    data class Services(
        @Json(name = "2")
        var x2: X2? = null,
        @Json(name = "4")
        var x4: X4? = null,
        @Json(name = "5")
        var x5: X5? = null,
        @Json(name = "6")
        var x6: X6? = null,
        @Json(name = "7")
        var x7: X7? = null,
        @Json(name = "78")
        var x78: X78? = null
    ) {
        data class X2(
            @Json(name = "NumberOfServices")
            var numberOfServices: String? = null,
            @Json(name = "ServiceID")
            var serviceID: String? = null,
            @Json(name = "ServiceName")
            var serviceName: String? = null
        )

        data class X4(
            @Json(name = "NumberOfServices")
            var numberOfServices: Int? = null,
            @Json(name = "ServiceID")
            var serviceID: String? = null,
            @Json(name = "ServiceName")
            var serviceName: String? = null
        )

        data class X5(
            @Json(name = "NumberOfServices")
            var numberOfServices: String? = null,
            @Json(name = "ServiceID")
            var serviceID: String? = null,
            @Json(name = "ServiceName")
            var serviceName: String? = null
        )

        data class X6(
            @Json(name = "NumberOfServices")
            var numberOfServices: String? = null,
            @Json(name = "ServiceID")
            var serviceID: String? = null,
            @Json(name = "ServiceName")
            var serviceName: String? = null
        )

        data class X7(
            @Json(name = "NumberOfServices")
            var numberOfServices: String? = null,
            @Json(name = "ServiceID")
            var serviceID: String? = null,
            @Json(name = "ServiceName")
            var serviceName: String? = null
        )

        data class X78(
            @Json(name = "NumberOfServices")
            var numberOfServices: String? = null,
            @Json(name = "ServiceID")
            var serviceID: String? = null,
            @Json(name = "ServiceName")
            var serviceName: String? = null
        )
    }
}

