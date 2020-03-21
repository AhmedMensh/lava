package com.android.pharous.app.lava.repositories

import com.android.pharous.app.lava.models.DataResult
import com.android.pharous.app.lava.models.LoginResponse
import com.android.pharous.app.lava.models.VerificationCodeRequest

interface ILavaRepo {

    suspend fun userLogin(mobileNumber : String) : DataResult<LoginResponse>
    suspend fun verifyPhoneNumber(verificationCodeRequest: VerificationCodeRequest) : DataResult<LoginResponse>
}