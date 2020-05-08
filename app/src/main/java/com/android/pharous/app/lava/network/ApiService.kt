package com.android.pharous.app.lava.network

import CardioProgramResponse
import com.android.pharous.app.lava.models.*
import com.android.pharous.app.lava.ui.workout.models.EvaluateProgramRequest
import retrofit2.http.*


interface ApiService {

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

    @GET("branch/index")
    suspend fun getBranches(@Query("AccessToken") token: String): ApiResponse<List<BranchResponse>>

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
}







