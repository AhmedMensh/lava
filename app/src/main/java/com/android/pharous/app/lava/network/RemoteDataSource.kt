package com.android.pharous.app.lava.network

import com.android.pharous.app.lava.models.VerificationCodeRequest


class RemoteDataSource(private val api: ApiService) {


    suspend fun userLogin(mobileNumber: String) = safeApiCall { api.userLogin(mobileNumber) }
    suspend fun verifyPhoneNumber(verificationCodeRequest: VerificationCodeRequest) = safeApiCall {
        api.verifyPhoneNumber(
            verificationCodeRequest.mobileNumber!!,
            verificationCodeRequest.verificationCode!!,
            verificationCodeRequest.accessToken!!
        )
    }
}