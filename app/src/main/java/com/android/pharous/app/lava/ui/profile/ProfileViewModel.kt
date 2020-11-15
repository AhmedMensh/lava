package com.android.pharous.app.lava.ui.profile

import com.android.pharous.app.lava.ui.workout.models.CardioProgramResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pharous.app.lava.models.*
import com.android.pharous.app.lava.repositories.ILavaRepo
import com.android.pharous.app.lava.ui.training.models.SessionResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(private val iLavaRepo: ILavaRepo) : ViewModel() {


    var error = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()

    private val languages = listOf("EN","AR")
    private val goals  = listOf("Lose Weight","Keep Fitness","Building Muscles")


    fun getLanguages() = languages
    fun getGoals() = goals
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
    fun updateUserProfile(profileRequest: ProfileRequest): LiveData<Boolean> {

        val data = MutableLiveData<Boolean>()

        isLoading.value = true
        viewModelScope.launch {

            when (val result = withContext(IO) { iLavaRepo.updateUserProfile(profileRequest) }) {

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

    fun getCities() : LiveData<List<CityResponse>>{

        val data = MutableLiveData<List<CityResponse>>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.getCities()}){

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

    fun getRegions() : LiveData<List<RegionResponse>>{

        val data = MutableLiveData<List<RegionResponse>>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.getRegions()}){

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



    fun getNationalities() : LiveData<List<NationalityResponse>>{

        val data = MutableLiveData<List<NationalityResponse>>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.getNationalities()}){

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


    fun getJobTitles() : LiveData<List<JobResponse>>{

        val data = MutableLiveData<List<JobResponse>>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.getJobTitles()}){

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

    fun getBranches() : LiveData<List<BranchResponse>>{

        val data = MutableLiveData<List<BranchResponse>>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.getBranches()}){

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