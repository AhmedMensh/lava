package com.android.pharous.app.lava.network

import com.android.pharous.app.lava.models.RegisterRequest
import com.android.pharous.app.lava.models.VerificationCodeRequest
import com.android.pharous.app.lava.ui.workout.models.EvaluateProgramRequest
import retrofit2.http.Field
import retrofit2.http.Query


class RemoteDataSource(private val api: ApiService) {


    suspend fun getCities() = safeApiCall { api.getCities() }
    suspend fun userLogin(mobileNumber: String) = safeApiCall { api.userLogin(mobileNumber) }


    suspend fun verifyPhoneNumber(verificationCodeRequest: VerificationCodeRequest) = safeApiCall {
        api.verifyPhoneNumber(
            verificationCodeRequest
        )
    }

    suspend fun getProfile(token: String) = safeApiCall { api.getProfile(token) }
    suspend fun getExerciseReservations(token: String) =
        safeApiCall { api.getExerciseReservations(token) }

    suspend fun updateReservation(token: String, id: String, canceled: String) =
        safeApiCall { api.updateReservation(token, id, canceled) }

    suspend fun getBranches(token: String) = safeApiCall { api.getBranches(token) }

    suspend fun register(registerRequest: RegisterRequest) =
        safeApiCall { api.register(registerRequest) }

    suspend fun getMembershipInfo(token: String) = safeApiCall { api.getMembershipInfo(token) }
    suspend fun suspendMembership(
        token: String,
        startDate: String,
        endDate: String
    ) = safeApiCall { api.suspendMembership(token, startDate, endDate) }

    suspend fun checkMembershipSuspension(token: String) =
        safeApiCall { api.checkMembershipSuspension(token) }

    suspend fun getMemberCardioPrograms(token: String) =
        safeApiCall { api.getMemberCardioPrograms(token) }

    suspend fun evaluateCardioProgram(evaluateProgramRequest: EvaluateProgramRequest) =
        safeApiCall { api.evaluateCardioProgram(evaluateProgramRequest) }

    suspend fun getSessions(token: String) = safeApiCall { api.getSessions(token) }


    suspend fun getMemberMeasurements(token: String) =
        safeApiCall { api.getMemberMeasurements(token) }

    suspend fun getMemberInbodyResults(token: String) =
        safeApiCall { api.getMemberInbodyResults(token) }

    suspend fun getExerciseSchedules(token: String, searchName: String) =
        safeApiCall { api.getExerciseSchedules(token, searchName) }

    suspend fun reserveExercise(token: String, exerciseScheduleID: String) =
        safeApiCall { api.reserveExercise(token, exerciseScheduleID) }

    suspend fun inviteFriendByMail(
        token: String,
        fullName: String,
        email: String,
        mobileNumber: String
    ) = safeApiCall { api.inviteFriendByMail(token, fullName, email, mobileNumber) }

    suspend fun inviteFriendBySMS(
        token: String,
        fullName: String,
        mobileNumber: String
    ) = safeApiCall { api.inviteFriendBySMS(token, fullName, mobileNumber) }


    suspend fun getBranchPackages(token: String, branchID: Int, type: Int) = safeApiCall { api.getBranchPackages(token, branchID, type) }
    suspend fun getPackagePeriods(token: String, packageID: Int) = safeApiCall { api.getPackagePeriods(token, packageID) }
    suspend fun getPackageDetails(token: String, peroidID: Int) = safeApiCall { api.getPackageDetails(token, peroidID) }
    suspend fun checkStartDate(token: String, peroidID: Int , startDate : String) = safeApiCall { api.checkStartDate(token, peroidID,startDate) }
    suspend fun createContract(token: String, peroidID: Int ,branchID: Int, startDate : String) = safeApiCall { api.createContract(token, peroidID,branchID,startDate) }
}