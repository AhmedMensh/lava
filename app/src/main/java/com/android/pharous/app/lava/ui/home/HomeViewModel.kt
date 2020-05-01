package com.android.pharous.app.lava.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pharous.app.lava.models.DataResult
import com.android.pharous.app.lava.models.ExerciseReservationResponse
import com.android.pharous.app.lava.models.MembershipInfoResponse
import com.android.pharous.app.lava.models.ProfileResponse
import com.android.pharous.app.lava.repositories.ILavaRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val iLavaRepo: ILavaRepo) : ViewModel() {

    var error = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()

    fun getProfile() : LiveData<ProfileResponse>{

        val data = MutableLiveData<ProfileResponse>()

        isLoading.value = true
        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.getProfile() }){

                is DataResult.Success ->{
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

    fun getExerciseReservations() : LiveData<List<ExerciseReservationResponse>>{

        val data = MutableLiveData<List<ExerciseReservationResponse>>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.getExerciseReservations() }){

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


    fun getMembershipInfo() : LiveData<MembershipInfoResponse>{

        val data = MutableLiveData<MembershipInfoResponse>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.getMembershipInfo() }){

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