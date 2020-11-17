package com.android.pharous.app.lava.network

import com.android.pharous.app.lava.models.*
import com.android.pharous.app.lava.ui.workout.models.EvaluateProgramRequest
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query


class RemoteDataSource(private val api: ApiService) {


    suspend fun getCities() = safeApiCall { api.getCities() }
    suspend fun getRegions(token: String) = safeApiCall { api.getRegions(token) }
    suspend fun getNationalities(token: String) = safeApiCall { api.getNationalities(token) }
    suspend fun getJobTitles(token: String) = safeApiCall { api.getJobTitles(token) }


    suspend fun userLogin(mobileNumber: String) = safeApiCall { api.userLogin(mobileNumber) }


    suspend fun verifyPhoneNumber(verificationCodeRequest: VerificationCodeRequest) = safeApiCall {
        api.verifyPhoneNumber(
            verificationCodeRequest
        )
    }

    suspend fun getProfile(token: String) = safeApiCall { api.getProfile(token) }
    suspend fun updateUserProfile(profileRequest: ProfileRequest) =
        safeApiCall { api.updateUserProfile(profileRequest) }

    suspend fun getExerciseReservations(token: String) =
        safeApiCall { api.getExerciseReservations(token) }

    suspend fun updateReservation(token: String, id: String, canceled: String, isAttended: String) =
        safeApiCall { api.updateReservation(token, id, canceled, isAttended) }

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

    suspend fun updateInbodyResult(inbodyResultRequest: InbodyResultRequest) =
        safeApiCall { api.updateInbodyResult(inbodyResultRequest) }

    suspend fun getMemberCardioPrograms(token: String) =
        safeApiCall { api.getMemberCardioPrograms(token) }

    suspend fun evaluateCardioProgram(evaluateProgramRequest: EvaluateProgramRequest) =
        safeApiCall { api.evaluateCardioProgram(evaluateProgramRequest) }

    suspend fun getPersonalTrainingSessions(token: String) =
        safeApiCall { api.getPersonalTrainingSessions(token) }

    suspend fun updatePersonalTrainingReservation(
        token: String,
        id: String,
        canceled: String,
        IsAttended: String
    ) = safeApiCall { api.updatePersonalTrainingReservation(token, id, canceled, IsAttended) }

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

    suspend fun getTotalPoints(token: String) = safeApiCall { api.getTotalPoints(token) }
    suspend fun getPointsHistory(token: String) = safeApiCall { api.getPointsHistory(token) }

    suspend fun getBranchPackages(token: String, branchID: Int, type: Int) =
        safeApiCall { api.getBranchPackages(token, branchID, type) }

    suspend fun getPackagePeriods(token: String, packageID: Int) =
        safeApiCall { api.getPackagePeriods(token, packageID) }

    suspend fun getPackageDetails(token: String, peroidID: Int) =
        safeApiCall { api.getPackageDetails(token, peroidID) }

    suspend fun checkStartDate(token: String, peroidID: Int, startDate: String) =
        safeApiCall { api.checkStartDate(token, peroidID, startDate) }

    suspend fun createContract(token: String, peroidID: Int, branchID: Int, startDate: String) =
        safeApiCall { api.createContract(token, peroidID, branchID, startDate) }
}