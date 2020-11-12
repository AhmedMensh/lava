package com.android.pharous.app.lava.ui.home

import com.android.pharous.app.lava.ui.workout.models.CardioProgramResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pharous.app.lava.models.DataResult
import com.android.pharous.app.lava.models.ExerciseReservationResponse
import com.android.pharous.app.lava.models.MembershipInfoResponse
import com.android.pharous.app.lava.models.ProfileResponse
import com.android.pharous.app.lava.repositories.ILavaRepo
import com.android.pharous.app.lava.ui.training.models.SessionResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val iLavaRepo: ILavaRepo) : ViewModel() {

    var error = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()

    fun getProfile(): LiveData<ProfileResponse> {

        val data = MutableLiveData<ProfileResponse>()

        isLoading.value = true
        viewModelScope.launch {

            when (val result = withContext(IO) { iLavaRepo.getProfile() }) {

                is DataResult.Success -> {
                    data.value = result.content
                    error.value = null
                    isLoading.value = false
                }

                is DataResult.Error -> {
                    data.value = null
                    isLoading.value = false
                    error.value = result.exception.message
                }
            }
        }
        return data
    }

    fun getPersonalTrainingSessions(): LiveData<List<SessionResponse>> {

        val data = MutableLiveData<List<SessionResponse>>()
        isLoading.value = true
        viewModelScope.launch {
            when (val result = withContext(IO) { iLavaRepo.getPersonalTrainingSessions() }) {

                is DataResult.Success -> {
                    isLoading.value = false
                    data.value = result.content
                    error.value = null
                }
                is DataResult.Error -> {
                    isLoading.value = false
                    data.value = null
                    error.value = result.exception.message
                }
            }
        }
        return data
    }

    fun getMemberCardioPrograms(): LiveData<List<CardioProgramResponse>> {
        val data = MutableLiveData<List<CardioProgramResponse>>()

        viewModelScope.launch {
            when (val result = withContext(IO) { iLavaRepo.getMemberCardioPrograms() }) {

                is DataResult.Success -> {
                    data.value = result.content
                    error.value = null
                }
                is DataResult.Error -> {
                    data.value = null
                    error.value = result.exception.message
                }
            }
        }
        return data
    }

    fun getExerciseReservations(): LiveData<List<ExerciseReservationResponse>> {

        val data = MutableLiveData<List<ExerciseReservationResponse>>()

        viewModelScope.launch {

            when (val result = withContext(IO) { iLavaRepo.getExerciseReservations() }) {

                is DataResult.Success -> {
                    data.value = result.content
                    error.value = null
                }

                is DataResult.Error -> {
                    data.value = null
                    error.value = result.exception.message
                }
            }
        }
        return data
    }


    fun updateReservations(id: String, canceled: String = "0",isAttended: String = "0"): LiveData<Boolean> {

        val data = MutableLiveData<Boolean>()

        viewModelScope.launch {

            when (val result = withContext(IO) { iLavaRepo.updateReservation(id, canceled,isAttended) }) {

                is DataResult.Success -> {
                    data.value = result.content
                    error.value = null
                }

                is DataResult.Error -> {
                    data.value = null
                    error.value = result.exception.message
                }
            }
        }
        return data
    }

     fun updatePersonalTrainingReservation(
        id: String,
        canceled: String = "0",
        isAttended: String = "0"
    ): LiveData<Boolean> {

        val data = MutableLiveData<Boolean>()

        viewModelScope.launch {

            when (val result = withContext(IO) {
                iLavaRepo.updatePersonalTrainingReservation(
                    id,
                    canceled,
                    isAttended
                )
            }) {

                is DataResult.Success -> {
                    data.value = result.content
                    error.value = null
                }

                is DataResult.Error -> {
                    data.value = null
                    error.value = result.exception.message
                }
            }
        }
        return data
    }

    fun getMembershipInfo(): LiveData<MembershipInfoResponse> {

        val data = MutableLiveData<MembershipInfoResponse>()

        viewModelScope.launch {

            when (val result = withContext(IO) { iLavaRepo.getMembershipInfo() }) {

                is DataResult.Success -> {
                    data.value = result.content
                    error.value = null
                }

                is DataResult.Error -> {
                    data.value = null
                    error.value = result.exception.message
                }
            }
        }
        return data
    }
}