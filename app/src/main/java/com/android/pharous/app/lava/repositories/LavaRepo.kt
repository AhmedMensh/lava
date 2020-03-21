package com.android.pharous.app.lava.repositories

import android.content.Context
import com.android.pharous.app.lava.models.DataResult
import com.android.pharous.app.lava.models.LoginResponse
import com.android.pharous.app.lava.models.VerificationCodeRequest
import com.android.pharous.app.lava.network.RemoteDataSource

class LavaRepo(private val remoteDataSource: RemoteDataSource , private val context: Context) : ILavaRepo {


    override suspend fun userLogin(mobileNumber: String): DataResult<LoginResponse> {

        return when(val result = remoteDataSource.userLogin(mobileNumber)){
            is DataResult.Success -> DataResult.Success(result.content)
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }

    override suspend fun verifyPhoneNumber(verificationCodeRequest: VerificationCodeRequest): DataResult<LoginResponse> {

        return when(val result = remoteDataSource.verifyPhoneNumber(verificationCodeRequest)){
            is DataResult.Success -> DataResult.Success(result.content)
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }
}