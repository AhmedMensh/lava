package com.android.pharous.app.lava.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pharous.app.lava.models.DataResult
import com.android.pharous.app.lava.models.LoginResponse
import com.android.pharous.app.lava.repositories.ILavaRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val iLavaRepo: ILavaRepo) : ViewModel() {

    var error = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()

    fun login(mobileNumber : String) : LiveData<LoginResponse>{

        isLoading.value = true
        val data = MutableLiveData<LoginResponse>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.userLogin(mobileNumber)}){

                is DataResult.Success -> {
                    data.value = result.content
                    error.value = null
                    isLoading.value =false
                }

                is DataResult.Error -> {
                    data.value = null
                    error.value= result.exception.message
                    isLoading.value = false
                }
            }
        }
        return data
    }


}