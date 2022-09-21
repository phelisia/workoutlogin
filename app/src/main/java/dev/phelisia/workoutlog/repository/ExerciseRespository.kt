package dev.phelisia.workoutlog.repository

import dev.phelisia.workoutlog.api.ApiClient
import dev.phelisia.workoutlog.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRespository {
    val apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun  fetchExercisesCategories(accessToken:String)= withContext(Dispatchers.IO){

        return@withContext apiClient.fetchExerciseCategories(accessToken)

    }
}