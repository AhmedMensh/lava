package com.android.pharous.app.lava.ui.point

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pharous.app.lava.models.BranchResponse
import com.android.pharous.app.lava.models.DataResult
import com.android.pharous.app.lava.models.PackageDetailsResponse
import com.android.pharous.app.lava.repositories.ILavaRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PointViewModel(private val iLavaRepo: ILavaRepo) : ViewModel() {

    var error = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()

    fun getBranches() : LiveData<List<BranchResponse>>{

        isLoading.value = true
        val data = MutableLiveData<List<BranchResponse>>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.getBranches()}){

                is DataResult.Success -> {
                    isLoading.value = false
                    error.value = null
                    data.value = result.content
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

    fun getBranchPackages(branchId : Int ,type : Int) : LiveData<Map<String,String>>{

        isLoading.value = true
        val data = MutableLiveData<Map<String,String>>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.getBranchPackages(branchId ,type)}){

                is DataResult.Success -> {
                    isLoading.value = false
                    error.value = null
                    data.value = result.content
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

    fun getPackagePeriods(packageId : Int) : LiveData<Map<String,Int>>{

        isLoading.value = true
        val data = MutableLiveData<Map<String,Int>>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.getPackagePeriods(packageId)}){

                is DataResult.Success -> {
                    isLoading.value = false
                    error.value = null
                    data.value = result.content
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

    fun getPackageDetails(periodId : Int) : LiveData<PackageDetailsResponse>{

        isLoading.value = true
        val data = MutableLiveData<PackageDetailsResponse>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.getPackageDetails(periodId)}){

                is DataResult.Success -> {
                    isLoading.value = false
                    error.value = null
                    data.value = result.content
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

    fun checkStartDate(periodId : Int, startDate : String) : LiveData<Int>{

        isLoading.value = true
        val data = MutableLiveData<Int>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.checkStartDate(periodId,startDate)}){

                is DataResult.Success -> {
                    isLoading.value = false
                    error.value = null
                    data.value = result.content
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

    fun createContract(periodId : Int,branchId : Int, startDate : String) : LiveData<Int>{

        isLoading.value = true
        val data = MutableLiveData<Int>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.createContract(periodId,branchId,startDate)}){

                is DataResult.Success -> {
                    isLoading.value = false
                    error.value = null
                    data.value = result.content
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