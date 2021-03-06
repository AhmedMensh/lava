package com.android.pharous.app.lava.repositories

import com.android.pharous.app.lava.ui.workout.models.CardioProgramResponse
import com.android.pharous.app.lava.models.*
import com.android.pharous.app.lava.network.safeApiCall
import com.android.pharous.app.lava.ui.measurement.MemberInbodyresultResponse
import com.android.pharous.app.lava.ui.measurement.MemberMeasurementResponse
import com.android.pharous.app.lava.ui.training.models.ExerciseScheduleResponse
import com.android.pharous.app.lava.ui.training.models.SessionResponse
import com.android.pharous.app.lava.ui.workout.models.EvaluateProgramRequest

interface ILavaRepo {

    suspend fun getCities() : DataResult<List<CityResponse>>
    suspend fun getRegions() : DataResult<List<RegionResponse>>
    suspend fun getNationalities() : DataResult<List<NationalityResponse>>
    suspend fun getJobTitles() : DataResult<List<JobResponse>>
    suspend fun userLogin(mobileNumber : String) : DataResult<LoginResponse>
    suspend fun verifyPhoneNumber(verificationCodeRequest: VerificationCodeRequest) : DataResult<LoginResponse>
    suspend fun getProfile() : DataResult<ProfileResponse>
    suspend fun updateUserProfile(profileRequest: ProfileRequest) : DataResult<Boolean>
    suspend fun getExerciseReservations() : DataResult<List<ExerciseReservationResponse>>
    suspend fun updateReservation(id : String , canceled : String,isAttended: String) : DataResult<Boolean>
    suspend fun getBranches() :DataResult<List<BranchResponse>>
    suspend fun register(request: RegisterRequest) : DataResult<Boolean>
    suspend fun getMembershipInfo() : DataResult<MembershipInfoResponse>
    suspend fun suspendMembership(startDate: String, endDate: String) : DataResult<Result>
    suspend fun checkMembershipSuspension() : DataResult<Result>
    suspend fun getMemberCardioPrograms() : DataResult<List<CardioProgramResponse>>
    suspend fun evaluateCardioProgram(evaluateProgramRequest: EvaluateProgramRequest) : DataResult<Boolean>
    suspend fun getPersonalTrainingSessions() : DataResult<List<SessionResponse>>
    suspend fun updatePersonalTrainingReservation(id: String, canceled: String, IsAttended: String) : DataResult<Boolean>
    suspend fun getMemberMeasurements() : DataResult<List<MemberMeasurementResponse>>
    suspend fun getMemberInbodyResults() : DataResult<List<MemberInbodyresultResponse>>
    suspend fun getExerciseSchedules(searchName : String) : DataResult<List<ExerciseScheduleResponse>>
    suspend fun reserveExercise(exerciseScheduleID : String) : DataResult<Boolean>
    suspend fun inviteFriendByMail(fullName: String, email: String, mobileNumber: String) : DataResult<Result>
    suspend fun inviteFriendBySMS(fullName: String, mobileNumber: String) : DataResult<Result>
    suspend fun getTotalPoints() : DataResult<TotalPointResponse>
    suspend fun getPointsHistory() : DataResult<List<PointHistoryResponse>>
    suspend fun getBranchPackages(branchID: Int, type: Int) : DataResult<Map<String ,String>>
    suspend fun getPackagePeriods(packageID: Int) : DataResult<Map<String ,Int>>
    suspend fun getPackageDetails(peroidID: Int) : DataResult<PackageDetailsResponse>
    suspend fun checkStartDate(peroidID: Int , startDate : String) : DataResult<Boolean>
    suspend fun createContract(peroidID: Int ,branchID: Int, startDate : String) : DataResult<Boolean>
    suspend fun updateInbodyResult(inbodyResultRequest: InbodyResultRequest)  : DataResult<Boolean>
    suspend fun addInbodyResult(inbodyResultRequest: InbodyResultRequest)  : DataResult<Boolean>
    suspend fun addCardioReadout(cardioRequest: CardioRequest)  : DataResult<Boolean>
    suspend fun addBodyBuildingReadout(bodyBuildingRequest: BodyBuildingRequest)  : DataResult<Boolean>
}