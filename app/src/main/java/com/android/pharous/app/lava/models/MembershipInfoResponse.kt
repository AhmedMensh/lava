package com.android.pharous.app.lava.models


import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class MembershipInfoResponse(
    @field:Json(name = "BranchID")
    val branchID: Long? = null,

    @field:Json(name = "BranchName")
    val branchName: String? = null,

    @field:Json(name = "StartDate")
    val startDate: String? = null,

    @field:Json(name = "EndDate")
    val endDate: String? = null,

    @field:Json(name = "CreationDate")
    val creationDate: String? = null,

    @field:Json(name = "Period")
    val period: Long? = null,

    @field:Json(name = "Services")
    val services: Map<String, Service>? = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        TODO("services")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(branchID)
        parcel.writeString(branchName)
        parcel.writeString(startDate)
        parcel.writeString(endDate)
        parcel.writeString(creationDate)
        parcel.writeValue(period)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MembershipInfoResponse> {
        override fun createFromParcel(parcel: Parcel): MembershipInfoResponse {
            return MembershipInfoResponse(parcel)
        }

        override fun newArray(size: Int): Array<MembershipInfoResponse?> {
            return arrayOfNulls(size)
        }
    }
}

data class Service(
    @field:Json(name = "ServiceID")
    val serviceID: String ? = null,

    @field:Json(name = "ServiceName")
    val serviceName: String ? = null

//    @field:Json(name = "NumberOfServices")
//    val numberOfServices: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(serviceID)
        parcel.writeString(serviceName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Service> {
        override fun createFromParcel(parcel: Parcel): Service {
            return Service(parcel)
        }

        override fun newArray(size: Int): Array<Service?> {
            return arrayOfNulls(size)
        }
    }
}

