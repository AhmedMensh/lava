package com.android.pharous.app.lava.network

import com.android.pharous.app.lava.models.*
import retrofit2.http.*


interface ApiService {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun userLogin(@Field("MobileNumber") mobileNumber: String): ApiResponse<LoginResponse>


//    @POST("user/verify-token")
//    suspend fun verifyPhoneNumber(@Body verificationCodeRequest: VerificationCodeRequest) : ApiResponse<LoginResponse>

    @FormUrlEncoded
    @POST("user/verify-token")
    suspend fun verifyPhoneNumber(
        @Field("MobileNumber") MobileNumber: String,
        @Field("VerificationCode") VerificationCode: String,
        @Field("AccessToken") AccessToken: String
    ): ApiResponse<LoginResponse>

    @GET("user/profile")
    suspend fun getProfile(@Query("AccessToken") token : String) : ApiResponse<ProfileResponse>

    @GET("exercise/view")
    suspend fun getExerciseReservations(@Query("AccessToken") token : String) : ApiResponse<List<ExerciseReservationResponse>>
}







