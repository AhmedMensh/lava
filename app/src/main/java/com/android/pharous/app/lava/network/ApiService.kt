package com.android.pharous.app.lava.network

import com.android.pharous.app.lava.models.ApiResponse
import com.android.pharous.app.lava.models.LoginResponse
import com.android.pharous.app.lava.models.VerificationCodeRequest
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


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

}







