package com.android.pharous.app.lava.ui.measurement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pharous.app.lava.models.DataResult
import com.android.pharous.app.lava.models.InbodyResultRequest
import com.android.pharous.app.lava.repositories.ILavaRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MeasurementViewModel(private val iLavaRepo: ILavaRepo) : ViewModel() {

    var error = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()

    fun getMemberMeasurements() : LiveData<List<MemberMeasurementResponse>>{

        val data = MutableLiveData<List<MemberMeasurementResponse>>()
        isLoading.value = true

        viewModelScope.launch {
            when(val result = withContext(IO) { iLavaRepo.getMemberMeasurements()}){

                is DataResult.Success-> {
                    data.value = result.content
                    error.value = null
                    isLoading.value = false
                }
                is DataResult.Error -> {
                    data.value = null
                    error.value = result.exception.message
                    isLoading.value = false
                }
            }
        }
        return data
    }


    fun getMemberInBodyResults() : LiveData<List<MemberInbodyresultResponse>>{

        val data = MutableLiveData<List<MemberInbodyresultResponse>>()
        isLoading.value = true

        viewModelScope.launch {
            when(val result = withContext(IO) { iLavaRepo.getMemberInbodyResults()}){

                is DataResult.Success-> {
                    data.value = result.content
                    error.value = null
                    isLoading.value = false
                }
                is DataResult.Error -> {
                    data.value = null
                    error.value = result.exception.message
                    isLoading.value = false
                }
            }
        }
        return data
    }

    fun updateMemberInBodyResults(inbodyResultRequest: InbodyResultRequest) : LiveData<Boolean>{

        val data = MutableLiveData<Boolean>()
        isLoading.value = true

        viewModelScope.launch {
            when(val result = withContext(IO) { iLavaRepo.updateInbodyResult(inbodyResultRequest)}){

                is DataResult.Success-> {
                    data.value = result.content
                    error.value = null
                    isLoading.value = false
                }
                is DataResult.Error -> {
                    data.value = null
                    error.value = result.exception.message
                    isLoading.value = false
                }
            }
        }
        return data
    }
}