package com.android.pharous.app.lava.ui.training

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pharous.app.lava.models.DataResult
import com.android.pharous.app.lava.repositories.ILavaRepo
import com.android.pharous.app.lava.ui.training.models.ExerciseScheduleResponse
import com.android.pharous.app.lava.ui.training.models.SessionResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrainingViewModel(private val iLavaRepo: ILavaRepo) : ViewModel() {

    val searchKey = MutableLiveData<String>()
    var error = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()

    fun getPersonalTrainingSessions() : LiveData<List<SessionResponse>>{

        val data = MutableLiveData<List<SessionResponse>>()
        isLoading.value = true
        viewModelScope.launch {
            when(val result = withContext(IO) { iLavaRepo.getPersonalTrainingSessions()}){

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



    fun getExerciseSchedules(searchName : String) : LiveData<List<ExerciseScheduleResponse>>{

        val data = MutableLiveData<List<ExerciseScheduleResponse>>()
        isLoading.value = true
        viewModelScope.launch {
            when(val result = withContext(IO) { iLavaRepo.getExerciseSchedules(searchName)}){

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

    fun reserveExercise(exerciseScheduleId: String) : LiveData<Boolean>{

        val data = MutableLiveData<Boolean>()
        isLoading.value = true
        viewModelScope.launch {
            when(val result = withContext(IO) { iLavaRepo.reserveExercise(exerciseScheduleId)}){

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
}