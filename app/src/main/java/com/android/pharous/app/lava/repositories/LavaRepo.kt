package com.android.pharous.app.lava.repositories

import CardioProgramResponse
import android.content.Context
import com.android.pharous.app.lava.common.Constants
import com.android.pharous.app.lava.common.SharedPreferencesManager
import com.android.pharous.app.lava.models.*
import com.android.pharous.app.lava.network.RemoteDataSource
import com.android.pharous.app.lava.ui.measurement.MemberInbodyresultResponse
import com.android.pharous.app.lava.ui.measurement.MemberMeasurementResponse
import com.android.pharous.app.lava.ui.training.models.SessionResponse
import com.android.pharous.app.lava.ui.workout.models.EvaluateProgramRequest

class LavaRepo(private val remoteDataSource: RemoteDataSource, private val context: Context) :
    ILavaRepo {

    var token: String = SharedPreferencesManager.getStringValue(context, Constants.TOKEN)

    override suspend fun getCities(): DataResult<List<CityResponse>> {
        return when (val result = remoteDataSource.getCities()) {
            is DataResult.Success -> DataResult.Success(result.content)
            is DataResult.Error -> DataResult.Error(result.exception)
        }

    }

    override suspend fun userLogin(mobileNumber: String): DataResult<LoginResponse> {

        return when (val result = remoteDataSource.userLogin(mobileNumber)) {
            is DataResult.Success -> {
                SharedPreferencesManager.setStringValue(
                    context, Constants.TOKEN,
                    result.content?.accessToken
                )
                DataResult.Success(result.content)
            }
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }

    override suspend fun verifyPhoneNumber(verificationCodeRequest: VerificationCodeRequest): DataResult<LoginResponse> {

//        verificationCodeRequest.AccessToken = token
        return when (val result = remoteDataSource.verifyPhoneNumber(verificationCodeRequest)) {
            is DataResult.Success -> {
                SharedPreferencesManager.setStringValue(
                    context, Constants.TOKEN,
                    result.content?.accessToken
                )
                DataResult.Success(result.content)
            }
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }

    override suspend fun getProfile(): DataResult<ProfileResponse> {

        return when (val result = remoteDataSource.getProfile(token)) {
            is DataResult.Success -> {
                SharedPreferencesManager.setStringValue(
                    context,
                    Constants.USER_NAME,
                    result.content?.fullName
                )
                DataResult.Success(result.content)
            }
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }

    override suspend fun getExerciseReservations(): DataResult<List<ExerciseReservationResponse>> {

        return when (val result = remoteDataSource.getExerciseReservations(token)) {
            is DataResult.Success -> DataResult.Success(result.content)
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }

    override suspend fun getBranches(): DataResult<List<BranchResponse>> {

        return when (val result = remoteDataSource.getBranches(token)) {

            is DataResult.Success -> DataResult.Success(result.content)
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }

    override suspend fun register(request: RegisterRequest): DataResult<Boolean> {

        return when (val result = remoteDataSource.register(request)) {
            is DataResult.Success -> {
                SharedPreferencesManager.setStringValue(
                    context, Constants.TOKEN,
                    result.content?.accessToken
                )
                DataResult.Success(true)
            }
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }

    override suspend fun getMembershipInfo(): DataResult<MembershipInfoResponse> {

        return when (val result = remoteDataSource.getMembershipInfo(token)) {
            is DataResult.Success -> DataResult.Success(result.content)
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }

    override suspend fun getMemberCardioPrograms(): DataResult<List<CardioProgramResponse>> {

        return when (val result = remoteDataSource.getMemberCardioPrograms(token)) {
            is DataResult.Success -> {
                DataResult.Success(result.content?.values?.toList())
            }
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }

    override suspend fun evaluateCardioProgram(evaluateProgramRequest: EvaluateProgramRequest): DataResult<Boolean> {
        evaluateProgramRequest.accessToken = token
        return when (val result = remoteDataSource.evaluateCardioProgram(evaluateProgramRequest)) {
            is DataResult.Success -> DataResult.Success(true)
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }

    override suspend fun getSessions(): DataResult<List<SessionResponse>> {
        return when (val result = remoteDataSource.getSessions(token)) {
            is DataResult.Success -> DataResult.Success(result.content)
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }

    override suspend fun getMemberMeasurements(): DataResult<List<MemberMeasurementResponse>> {
        return when (val result = remoteDataSource.getMemberMeasurements(token)) {
            is DataResult.Success -> DataResult.Success(result.content)
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }

    override suspend fun getMemberInbodyResults(): DataResult<List<MemberInbodyresultResponse>> {
        return when (val result = remoteDataSource.getMemberInbodyResults(token)) {
            is DataResult.Success -> DataResult.Success(result.content)
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }
}