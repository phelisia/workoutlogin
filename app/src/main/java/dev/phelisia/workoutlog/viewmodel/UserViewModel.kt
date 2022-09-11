package dev.phelisia.workoutlog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.phelisia.workoutlog.models.LoginRequest
import dev.phelisia.workoutlog.models.LoginResponse
import dev.phelisia.workoutlog.models.RegisterRequets
import dev.phelisia.workoutlog.models.RegisterResponse
import dev.phelisia.workoutlog.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel :ViewModel(){
    val userRepository=UserRepository()
    var loginResponseLiveData=MutableLiveData<LoginResponse>()
    val loginErrorLiveData=MutableLiveData<String?>()
    var registerResponseLiveData=MutableLiveData<RegisterResponse>()
    val registerErrorLiveData=MutableLiveData<String?>()

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response=userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                loginResponseLiveData.postValue(response.body())

            }else{
                val error=response.errorBody()?.string()
                loginErrorLiveData.postValue(error)
            }
        }

    }

    fun registerUser(registerRequets: RegisterRequets){
        viewModelScope.launch {
            val response=userRepository.registerUser(registerRequets)
            if (response.isSuccessful){
                registerResponseLiveData.postValue(response.body())

            }else{
                val error=response.errorBody()?.string()
                registerErrorLiveData.postValue(error)
            }
        }

    }

}