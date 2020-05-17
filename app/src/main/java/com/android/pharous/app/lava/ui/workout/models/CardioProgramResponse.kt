import com.beust.klaxon.*
import com.squareup.moshi.Json

//private fun <T> Klaxon.convert(k: kotlin.reflect.KClass<*>, fromJson: (JsonValue) -> T, toJson: (T) -> String, isUnion: Boolean = false) =
//    this.converter(object: Converter {
//        @Suppress("UNCHECKED_CAST")
//        override fun toJson(value: Any)        = toJson(value as T)
//        override fun fromJson(jv: JsonValue)   = fromJson(jv) as Any
//        override fun canConvert(cls: Class<*>) = cls == k.java || (isUnion && cls.superclass == k.java)
//    })
//
//private val klaxon = Klaxon()
//    .convert(BodybuildingProgrameDetailUnion::class, { BodybuildingProgrameDetailUnion.fromJson(it) }, { it.toJson() }, true)
//
//class Welcome(elements: Map<String, WelcomeValue>) : HashMap<String, WelcomeValue>(elements) {
//    public fun toJson() = klaxon.toJsonString(this)
//
//    companion object {
//        public fun fromJson(json: String) = Welcome (
//            klaxon.parseJsonObject(java.io.StringReader(json)) as Map<String, WelcomeValue>
//        )
//    }
//}


data class CardioProgramResponse (
    @field:Json(name = "ID")
    val id: String,

    @field:Json(name = "ProgramName")
    val programName: String,

    @field:Json(name = "NumberOfDays")
    val numberOfDays: String,

    @field:Json(name = "NumberOfWorkoutFinishers")
    val numberOfWorkoutFinishers: String,

    @field:Json(name = "WeekRepetition")
    val weekRepetition: String,

    @field:Json(name = "Note")
    val note: String? = null,

    @field:Json(name = "CreationDate")
    val creationDate: String,

    @field:Json(name = "CardioProgrameDetail")
    val cardioProgrameDetail: Map<String, CardioProgrameDetails>
//
//    @field:Json(name = "BodybuildingProgrameDetail")
//    val bodybuildingProgrameDetail: Map<String, BodybuildingProgramDetails>
)

//sealed class BodybuildingProgrameDetailUnion {
//    class AnythingArrayValue(val value: List<Any?>)                                                        : BodybuildingProgrameDetailUnion()
//    class BodybuildingProgrameDetailValueMapValue(val value: Map<String, BodybuildingProgrameDetailValue>) : BodybuildingProgrameDetailUnion()
//
//    public fun toJson(): String = klaxon.toJsonString(when (this) {
//        is AnythingArrayValue                      -> this.value
//        is BodybuildingProgrameDetailValueMapValue -> this.value
//    })
//
//    companion object {
//        public fun fromJson(jv: JsonValue): BodybuildingProgrameDetailUnion = when (jv.inside) {
//            is JsonArray<*> -> AnythingArrayValue(jv.array?.let { klaxon.parseFromJsonArray<Any?>(it) }!!)
//            is JsonObject -> BodybuildingProgrameDetailValueMapValue(jv.obj?.let { klaxon.parseFromJsonObject<Map<String, BodybuildingProgrameDetailValue>>(it) }!!)
//            else            -> throw IllegalArgumentException()
//        }
//    }
//}

data class BodybuildingProgramDetails (
    @field:Json(name = "Equipment")
    val equipment: Equipment,

    @field:Json(name = "Muscle")
    val muscle: Muscle,

    @field:Json(name = "Duration")
    val duration: Long? = null,

    @field:Json(name = "Repetition")
    val repetition: Long,

    @field:Json(name = "NumberOfRepetition")
    val numberOfRepetition: Long,

    @field:Json(name = "RestDuration")
    val restDuration: Long? = null,

    @field:Json(name = "Description")
    val description: String? = null,

    @field:Json(name = "Photo1")
    val photo1: String? = null,

    @field:Json(name = "Photo2")
    val photo2: String? = null
)

data class Equipment (
    @field:Json(name = "ID")
    val id: Long,

    @field:Json(name = "NameEN")
    val nameEN: String,

    @field:Json(name = "NameAR")
    val nameAR: String,

    @field:Json(name = "Weight")
    val weight: Long? = null,

    @field:Json(name = "Photo")
    val photo: String? = null
)

data class Muscle (
    @field:Json(name = "ID")
    val id: Long,

    @field:Json(name = "NameEN")
    val nameEN: String,

    @field:Json(name = "NameAR")
    val nameAR: String,

    @field:Json(name = "Photo")
    val photo: Any? = null
)

data class CardioProgrameDetails (
    @field:Json(name = "Equipment")
    val equipment: Equipment,

    @field:Json(name = "Duration")
    val duration: Long,

    @field:Json(name = "Speed")
    val speed: Long,

    @field:Json(name = "Level")
    val level: Long,

    @field:Json(name = "HeartRate")
    val heartRate: String
)
