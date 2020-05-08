package com.android.pharous.app.lava.repositories

import CardioProgramResponse
import com.android.pharous.app.lava.models.*
import com.android.pharous.app.lava.ui.workout.models.EvaluateProgramRequest
import retrofit2.http.Body

interface ILavaRepo {

    suspend fun userLogin(mobileNumber : String) : DataResult<LoginResponse>
    suspend fun verifyPhoneNumber(verificationCodeRequest: VerificationCodeRequest) : DataResult<LoginResponse>
    suspend fun getProfile() : DataResult<ProfileResponse>
    suspend fun getExerciseReservations() : DataResult<List<ExerciseReservationResponse>>
    suspend fun getBranches() :DataResult<List<BranchResponse>>
    suspend fun register(request: RegisterRequest) : DataResult<Boolean>
    suspend fun getMembershipInfo() : DataResult<MembershipInfoResponse>
    suspend fun getMemberCardioPrograms() : DataResult<List<CardioProgramResponse>>
    suspend fun evaluateCardioProgram(evaluateProgramRequest: EvaluateProgramRequest) : DataResult<Boolean>
}