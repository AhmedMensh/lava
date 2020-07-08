package com.android.pharous.app.lava.ui.invitation

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


class InvitationViewModel(private val iLavaRepo: ILavaRepo) : ViewModel(){

    var error = MutableLiveData<String>()

    fun inviteFriendBySMS(fullName : String , mobileNumber : String) : LiveData<Result>{

        val data = MutableLiveData<Result>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.inviteFriendBySMS(fullName, mobileNumber)}){

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

    fun inviteFriendByMail(fullName : String ,mail : String,  mobileNumber : String) : LiveData<Result>{

        val data = MutableLiveData<Result>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.inviteFriendByMail(fullName,mail, mobileNumber)}){

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