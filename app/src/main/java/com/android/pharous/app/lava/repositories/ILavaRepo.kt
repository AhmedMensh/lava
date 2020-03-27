package com.android.pharous.app.lava.repositories

import com.android.pharous.app.lava.models.*

interface ILavaRepo {

    suspend fun userLogin(mobileNumber : String) : DataResult<LoginResponse>
    suspend fun verifyPhoneNumber(verificationCodeRequest: VerificationCodeRequest) : DataResult<LoginResponse>
    suspend fun getProfile() : DataResult<ProfileResponse>
    suspend fun getExerciseReservations() : DataResult<List<ExerciseReservationResponse>>
}