package com.android.pharous.app.lava.ui.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.pharous.app.lava.models.DataResult
import com.android.pharous.app.lava.models.RegisterRequest
import com.android.pharous.app.lava.repositories.ILavaRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(private val iLavaRepo: ILavaRepo) : ViewModel() {

    var error = MutableLiveData<String>()

    fun register(registerRequest: RegisterRequest) : LiveData<Boolean>{

        val data = MutableLiveData<Boolean>()

        viewModelScope.launch {

            when(val result = withContext(IO) { iLavaRepo.register(registerRequest)}){

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