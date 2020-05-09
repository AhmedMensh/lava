package com.android.pharous.app.lava.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pharous.app.lava.models.DataResult
import com.android.pharous.app.lava.models.MembershipInfoResponse
import com.android.pharous.app.lava.repositories.ILavaRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel(private val iLavaRepo: ILavaRepo) : ViewModel(){

    var error = MutableLiveData<String>()
    fun getMembershipInfo() : LiveData<MembershipInfoResponse> {

        val data = MutableLiveData<MembershipInfoResponse>()

        viewModelScope.launch {

            when(val result = withContext(Dispatchers.IO) { iLavaRepo.getMembershipInfo() }){

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