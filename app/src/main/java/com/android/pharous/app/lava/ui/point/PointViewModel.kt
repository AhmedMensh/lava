package com.android.pharous.app.lava.ui.point

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pharous.app.lava.models.BranchResponse
import com.android.pharous.app.lava.models.DataResult
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
}