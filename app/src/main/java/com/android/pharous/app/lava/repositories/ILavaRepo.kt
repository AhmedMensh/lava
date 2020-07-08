package com.android.pharous.app.lava.repositories

import CardioProgramResponse
import com.android.pharous.app.lava.models.*
import com.android.pharous.app.lava.ui.measurement.MemberInbodyresultResponse
import com.android.pharous.app.lava.ui.measurement.MemberMeasurementResponse
import com.android.pharous.app.lava.ui.training.models.ExerciseScheduleResponse
import com.android.pharous.app.lava.ui.training.models.SessionResponse
import com.android.pharous.app.lava.ui.workout.models.EvaluateProgramRequest

interface ILavaRepo {

    suspend fun getCities() : DataResult<List<CityResponse>>

    suspend fun userLogin(mobileNumber : String) : DataResult<LoginResponse>
    suspend fun verifyPhoneNumber(verificationCodeRequest: VerificationCodeRequest) : DataResult<LoginResponse>
    suspend fun getProfile() : DataResult<ProfileResponse>
    suspend fun getExerciseReservations() : DataResult<List<ExerciseReservationResponse>>
    suspend fun updateReservation(id : String , canceled : String) : DataResult<String>
    suspend fun getBranches() :DataResult<List<BranchResponse>>
    suspend fun register(request: RegisterRequest) : DataResult<Boolean>
    suspend fun getMembershipInfo() : DataResult<MembershipInfoResponse>
    suspend fun suspendMembership(startDate: String, endDate: String) : DataResult<Result>
    suspend fun checkMembershipSuspension() : DataResult<Result>
    suspend fun getMemberCardioPrograms() : DataResult<List<CardioProgramResponse>>
    suspend fun evaluateCardioProgram(evaluateProgramRequest: EvaluateProgramRequest) : DataResult<Boolean>

    suspend fun getSessions() : DataResult<List<SessionResponse>>
    suspend fun getMemberMeasurements() : DataResult<List<MemberMeasurementResponse>>
    suspend fun getMemberInbodyResults() : DataResult<List<MemberInbodyresultResponse>>
    suspend fun getExerciseSchedules(searchName : String) : DataResult<List<ExerciseScheduleResponse>>
    suspend fun reserveExercise(exerciseScheduleID : String) : DataResult<Boolean>

    suspend fun inviteFriendByMail(fullName: String, email: String, mobileNumber: String) : DataResult<Result>
    suspend fun inviteFriendBySMS(fullName: String, mobileNumber: String) : DataResult<Result>


    suspend fun getBranchPackages(branchID: Int, type: Int) : DataResult<Map<String ,String>>
    suspend fun getPackagePeriods(packageID: Int) : DataResult<Map<String ,Int>>
    suspend fun getPackageDetails(peroidID: Int) : DataResult<PackageDetailsResponse>
    suspend fun checkStartDate(peroidID: Int , startDate : String) : DataResult<Int>
    suspend fun createContract(peroidID: Int ,branchID: Int, startDate : String) : DataResult<Int>
}