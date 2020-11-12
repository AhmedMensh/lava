package com.android.pharous.app.lava.network

import com.android.pharous.app.lava.ui.workout.models.CardioProgramResponse
import com.android.pharous.app.lava.models.*
import com.android.pharous.app.lava.ui.measurement.MemberInbodyresultResponse
import com.android.pharous.app.lava.ui.measurement.MemberMeasurementResponse
import com.android.pharous.app.lava.ui.training.models.ExerciseScheduleResponse
import com.android.pharous.app.lava.ui.training.models.SessionResponse
import com.android.pharous.app.lava.ui.workout.models.EvaluateProgramRequest
import retrofit2.http.*


interface ApiService {

    @GET("branch/index")
    suspend fun getBranches(@Query("AccessToken") token: String): ApiResponse<List<BranchResponse>>

    @GET("city/index")
    suspend fun getCities(): ApiResponse<List<CityResponse>>


    @FormUrlEncoded
    @POST("user/login")
    suspend fun userLogin(@Field("MobileNumber") mobileNumber: String): ApiResponse<LoginResponse>

    @POST("user/register")
    suspend fun register(@Body registerRequest: RegisterRequest): ApiResponse<LoginResponse>

    @POST("user/verify-token")
    suspend fun verifyPhoneNumber(@Body verificationCodeRequest: VerificationCodeRequest): ApiResponse<LoginResponse>

    @GET("user/profile")
    suspend fun getProfile(@Query("AccessToken") token: String): ApiResponse<ProfileResponse>

    @GET("exercise/view")
    suspend fun getExerciseReservations(@Query("AccessToken") token: String)
            : ApiResponse<List<ExerciseReservationResponse>>

    @FormUrlEncoded
    @POST("exercise/update")
    suspend fun updateReservation(
        @Field("AccessToken") token: String,
        @Field("ID") id: String,
        @Field("Canceled") canceled: String,
        @Field("IsAttended") isAttended: String
    ): ApiResponse<String>


    @GET("amtar-member-point/list-package")
    suspend fun getBranchPackages(
        @Query("AccessToken") token: String,
        @Query("BranchID") branchID: Int,
        @Query("Type") type: Int
    ): ApiResponse<Map<String, String>>

    @GET("amtar-member-point/list-period")
    suspend fun getPackagePeriods(
        @Query("AccessToken") token: String,
        @Query("PackageID") packageID: Int
    ): ApiResponse<Map<String, Int>>

    @GET("amtar-member-point/package-detail")
    suspend fun getPackageDetails(
        @Query("AccessToken") token: String,
        @Query("PeroidID") peroidID: Int
    ): ApiResponse<PackageDetailsResponse>


    @GET("amtar-member-point/startdatecheck")
    suspend fun checkStartDate(
        @Query("AccessToken") token: String,
        @Query("PeriodID") peroidID: Int,
        @Query("StartDate") StartDate: String
    ): ApiResponse<Boolean>

    @FormUrlEncoded
    @POST("amtar-member-point/create-contract")
    suspend fun createContract(
        @Field("AccessToken") token: String,
        @Field("PeriodID") peroidID: Int,
        @Field("BranchID") BranchID: Int,
        @Field("StartDate") StartDate: String
    ): ApiResponse<Boolean>

    @GET("amtar-level/get-level")
    suspend fun getTotalPoints(@Query("AccessToken") token: String): ApiResponse<TotalPointResponse>

    @GET("amtar-earn-event/get-earn-history")
    suspend fun getPointsHistory(@Query("AccessToken") token: String): ApiResponse<List<PointHistoryResponse>>

    @GET("membership/index")
    suspend fun getMembershipInfo(@Query("AccessToken") token: String): ApiResponse<MembershipInfoResponse>

    @GET("cardio/programs")
    suspend fun getMemberCardioPrograms(@Query("AccessToken") token: String)
            : ApiResponse<Map<String, CardioProgramResponse>>

    @POST("cardio/evaluate-program")
    suspend fun evaluateCardioProgram(@Body evaluateProgramRequest: EvaluateProgramRequest): ApiResponse<String>

    @GET("personal-training-reservations/view")
    suspend fun getPersonalTrainingSessions(@Query("AccessToken") token: String): ApiResponse<List<SessionResponse>>

    @FormUrlEncoded
    @POST("personal-training-reservations/update")
    suspend fun updatePersonalTrainingReservation(
        @Field("AccessToken") token: String,
        @Field("ID") id: String,
        @Field("Canceled") canceled: String,
        @Field("IsAttended") IsAttended: String
    ) : ApiResponse<String>

    @GET("training/measurements")
    suspend fun getMemberMeasurements(@Query("AccessToken") token: String): ApiResponse<List<MemberMeasurementResponse>>

    @GET("training/inbody-results")
    suspend fun getMemberInbodyResults(@Query("AccessToken") token: String): ApiResponse<List<MemberInbodyresultResponse>>


    @GET("exercise/index?BranchID=1&Year=2020&Month=07&Type=0")
    suspend fun getExerciseSchedules(
        @Query("AccessToken") token: String,
        @Query("SearchInput") searchName: String
    ): ApiResponse<List<ExerciseScheduleResponse>>

    @FormUrlEncoded
    @POST("exercise/reserve")
    suspend fun reserveExercise(
        @Field("AccessToken") token: String,
        @Field("ExerciseScheduleID") exerciseScheduleID: String
    ): ApiResponse<String>

    @FormUrlEncoded
    @POST("membership/invite-friend-bysms")
    suspend fun inviteFriendBySMS(
        @Field("AccessToken") token: String,
        @Field("FullName") fullName: String,
        @Field("MobileNumber") mobileNumber: String
    ): ApiResponse<Result>


    @FormUrlEncoded
    @POST("membership/invite-friend-bymail")
    suspend fun inviteFriendByMail(
        @Field("AccessToken") token: String,
        @Field("FullName") fullName: String,
        @Field("Email") email: String,
        @Field("MobileNumber") mobileNumber: String
    ): ApiResponse<Result>


    @FormUrlEncoded
    @POST("membership/create-suspension")
    suspend fun suspendMembership(
        @Field("AccessToken") token: String,
        @Field("StartDate") startDate: String,
        @Field("EndDate") endDate: String
    ): ApiResponse<Result>

    @FormUrlEncoded
    @POST("membership/check-suspension")
    suspend fun checkMembershipSuspension(@Field("AccessToken") token: String): ApiResponse<Result>
}







