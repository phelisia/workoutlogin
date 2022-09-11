package dev.phelisia.workoutlog.repository

import dev.phelisia.workoutlog.api.ApiClient
import dev.phelisia.workoutlog.api.ApiInterface
import dev.phelisia.workoutlog.models.LoginRequest
import dev.phelisia.workoutlog.models.LoginResponse
import dev.phelisia.workoutlog.models.RegisterRequets
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
    val apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun loginUser(loginRequest: LoginRequest)= withContext(Dispatchers.IO){
        val response=apiClient.login(loginRequest)
        return@withContext response
    }


    suspend fun registerUser(registerRequets: RegisterRequets)= withContext(Dispatchers.IO){
        val response=apiClient.registerUser(registerRequets)
        return@withContext response
    }
}