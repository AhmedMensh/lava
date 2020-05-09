package com.android.pharous.app.lava.network

import com.android.pharous.app.lava.models.RegisterRequest
import com.android.pharous.app.lava.models.VerificationCodeRequest
import com.android.pharous.app.lava.ui.workout.models.EvaluateProgramRequest



class RemoteDataSource(private val api: ApiService) {


    suspend fun getCities() = safeApiCall { api.getCities() }
    suspend fun userLogin(mobileNumber: String) = safeApiCall { api.userLogin(mobileNumber) }


    suspend fun verifyPhoneNumber(verificationCodeRequest: VerificationCodeRequest) = safeApiCall {
        api.verifyPhoneNumber(
           verificationCodeRequest
        )
    }

    suspend fun getProfile(token : String) = safeApiCall { api.getProfile(token) }
    suspend fun getExerciseReservations(token : String) = safeApiCall { api.getExerciseReservations(token) }

    suspend fun getBranches(token: String) = safeApiCall { api.getBranches(token) }

    suspend fun register(registerRequest: RegisterRequest) = safeApiCall { api.register(registerRequest) }

    suspend fun getMembershipInfo(token: String) = safeApiCall { api.getMembershipInfo(token) }

    suspend fun getMemberCardioPrograms(token: String) = safeApiCall { api.getMemberCardioPrograms(token) }

    suspend fun evaluateCardioProgram(evaluateProgramRequest: EvaluateProgramRequest) = safeApiCall { api.evaluateCardioProgram(evaluateProgramRequest) }

    suspend fun getSessions(token: String) = safeApiCall { api.getSessions(token) }


    suspend fun getMemberMeasurements(token: String) = safeApiCall { api.getMemberMeasurements(token) }
    suspend fun getMemberInbodyResults(token: String) = safeApiCall { api.getMemberInbodyResults(token) }
}