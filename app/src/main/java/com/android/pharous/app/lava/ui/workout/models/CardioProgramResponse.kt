package com.android.pharous.app.lava.ui.workout.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
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
    val cardioProgrameDetail: Map<String, CardioProgrameDetails> ? = null,

    @field:Json(name = "BodybuildingProgrameDetail")
    val bodybuildingProgrameDetail: Map<String, BodybuildingProgramDetails> ? = null
) : Parcelable


@Parcelize
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
) : Parcelable


@Parcelize
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
) : Parcelable

@Parcelize
data class Muscle (
    @field:Json(name = "ID")
    val id: Long,

    @field:Json(name = "NameEN")
    val nameEN: String,

    @field:Json(name = "NameAR")
    val nameAR: String,

    @field:Json(name = "Photo")
    val photo: String? = null
) : Parcelable


@Parcelize
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
    val heartRate: String,

    @field:Json(name = "DescriptionAR")
    val descriptionAR: String,

    @field:Json(name = "DescriptionEN")
    val descriptionEN: String
) : Parcelable
