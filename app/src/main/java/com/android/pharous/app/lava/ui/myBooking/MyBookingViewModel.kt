package com.android.pharous.app.lava.ui.myBooking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pharous.app.lava.models.DataResult
import com.android.pharous.app.lava.models.ExerciseReservationResponse
import com.android.pharous.app.lava.repositories.ILavaRepo
import com.android.pharous.app.lava.ui.training.models.SessionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyBookingViewModel(private val iLavaRepo: ILavaRepo) : ViewModel() {

    var error = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()

    fun getPersonalTrainingSessions(): LiveData<List<SessionResponse>> {

        val data = MutableLiveData<List<SessionResponse>>()
        isLoading.value = true
        viewModelScope.launch {
            when (val result = withContext(Dispatchers.IO) { iLavaRepo.getPersonalTrainingSessions() }) {

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

    fun getExerciseReservations(): LiveData<List<ExerciseReservationResponse>> {

        val data = MutableLiveData<List<ExerciseReservationResponse>>()

        viewModelScope.launch {

            when (val result = withContext(Dispatchers.IO) { iLavaRepo.getExerciseReservations() }) {

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