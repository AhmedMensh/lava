package com.android.pharous.app.lava.models


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

) {
    fun serviceList() : MutableList<Service>{
        var serviceList : MutableList<Service> = mutableListOf()
        services?.let {
            for ((k,v) in it){
                serviceList.add(v)
            }
        }
        return serviceList
    }
}

data class Service(
    @field:Json(name = "ServiceID")
    val serviceID: String,

    @field:Json(name = "ServiceName")
    val serviceName: String

//    @field:Json(name = "NumberOfServices")
//    val numberOfServices: String
)

//sealed class NumberOfServices {
//    class IntegerValue(val value: Long)  : NumberOfServices()
//    class StringValue(val value: String) : NumberOfServices()
//
//    public fun toJson(): String = klaxon.toJsonString(when (this) {
//        is IntegerValue -> this.value
//        is StringValue  -> this.value
//    })
//
//    companion object {
//        public fun fromJson(jv: JsonValue): NumberOfServices = when (jv.inside) {
//            is Int, is Long -> IntegerValue((jv.int?.toLong() ?: jv.longValue)!!)
//            is String       -> StringValue(jv.string!!)
//            else            -> throw IllegalArgumentException()
//        }
//    }
//}