package com.android.pharous.app.lava.repositories

import android.content.Context
import com.android.pharous.app.lava.common.Constants
import com.android.pharous.app.lava.common.SharedPreferencesManager
import com.android.pharous.app.lava.models.*
import com.android.pharous.app.lava.network.RemoteDataSource

class LavaRepo(private val remoteDataSource: RemoteDataSource , private val context: Context) : ILavaRepo {

    var token : String = SharedPreferencesManager.getStringValue(context,Constants.TOKEN)

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

    override suspend fun getProfile(): DataResult<ProfileResponse> {

        return when(val result = remoteDataSource.getProfile(token)){
            is DataResult.Success -> DataResult.Success(result.content)
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }

    override suspend fun getExerciseReservations(): DataResult<List<ExerciseReservationResponse>> {

        return when(val result = remoteDataSource.getExerciseReservations(token)){
            is DataResult.Success -> DataResult.Success(result.content)
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }
}