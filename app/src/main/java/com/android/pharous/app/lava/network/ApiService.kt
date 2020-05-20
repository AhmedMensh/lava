package com.android.pharous.app.lava.network

import CardioProgramResponse
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
    suspend fun register(@Body registerRequest: RegisterRequest) : ApiResponse<LoginResponse>

    @POST("user/verify-token")
    suspend fun verifyPhoneNumber(@Body verificationCodeRequest: VerificationCodeRequest) : ApiResponse<LoginResponse>

    @GET("user/profile")
    suspend fun getProfile(@Query("AccessToken") token: String): ApiResponse<ProfileResponse>

    @GET("exercise/view")
    suspend fun getExerciseReservations(@Query("AccessToken") token: String)
            : ApiResponse<List<ExerciseReservationResponse>>



    @GET("amtar-member-point/list-package")
    suspend fun getBranchPackages(
        @Query("AccessToken") token: String,
        @Query("BranchID") branchID: Int,
        @Query("type") type: Int
    )

    @GET("membership/index")
    suspend fun getMembershipInfo(@Query("AccessToken") token: String) : ApiResponse<MembershipInfoResponse>

    @GET("cardio/programs")
    suspend fun getMemberCardioPrograms(@Query("AccessToken") token: String)
            : ApiResponse<Map<String ,CardioProgramResponse>>

    @POST("cardio/evaluate-program")
    suspend fun evaluateCardioProgram(@Body evaluateProgramRequest: EvaluateProgramRequest) : ApiResponse<String>

    @GET("personal-training-reservations/view")
    suspend fun getSessions(@Query("AccessToken") token: String) : ApiResponse<List<SessionResponse>>

    @GET("training/measurements")
    suspend fun getMemberMeasurements(@Query("AccessToken") token: String) : ApiResponse<List<MemberMeasurementResponse>>

    @GET("training/inbody-results")
    suspend fun getMemberInbodyResults(@Query("AccessToken") token: String) : ApiResponse<List<MemberInbodyresultResponse>>


    @GET("exercise/index?BranchID=1&Year=2018&Month=02&Type=0")
    suspend fun getExerciseSchedules(@Query("AccessToken") token: String) : ApiResponse<List<ExerciseScheduleResponse>>
}







