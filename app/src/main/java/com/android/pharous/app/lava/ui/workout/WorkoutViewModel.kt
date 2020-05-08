package com.android.pharous.app.lava.ui.workout

import CardioProgramResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pharous.app.lava.models.DataResult
import com.android.pharous.app.lava.repositories.ILavaRepo
import com.android.pharous.app.lava.ui.workout.models.EvaluateProgramRequest
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WorkoutViewModel(private val iLavaRepo: ILavaRepo) : ViewModel(){

    var error = MutableLiveData<String>()

    fun getMemberCardioPrograms() : LiveData<List<CardioProgramResponse>>{
        val data = MutableLiveData<List<CardioProgramResponse>>()

        viewModelScope.launch {
            when(val result = withContext(IO) { iLavaRepo.getMemberCardioPrograms()}){

                is DataResult.Success ->{
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


    fun evaluateCardioProgram(evaluateProgramRequest: EvaluateProgramRequest) : LiveData<Boolean>{
        val data = MutableLiveData<Boolean>()

        viewModelScope.launch {
            when(val result = withContext(IO) { iLavaRepo.evaluateCardioProgram(evaluateProgramRequest)}){

                is DataResult.Success ->{
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