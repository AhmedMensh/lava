package com.android.pharous.app.lava.ui.membership

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pharous.app.lava.models.DataResult
import com.android.pharous.app.lava.models.Result
import com.android.pharous.app.lava.repositories.ILavaRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MembershipViewModel(private val iLavaRepo: ILavaRepo) : ViewModel() {

    var error = MutableLiveData<String>()

    fun suspendMembership(startDate : String , endDate : String) : LiveData<Result>{

        var data = MutableLiveData<Result>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.suspendMembership(startDate, endDate)}){

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

    fun checkMembershipSuspension() : LiveData<Result>{

        var data = MutableLiveData<Result>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.checkMembershipSuspension()}){

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