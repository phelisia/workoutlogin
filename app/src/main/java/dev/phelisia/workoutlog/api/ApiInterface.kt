package dev.phelisia.workoutlog.api

import dev.phelisia.workoutlog.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequets): Response<RegisterResponse>

    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/profile")
    suspend fun profile(@Body profileRequest: ProfileRequest): Response<ProfileResponse>


    @GET("/exercises")
    suspend fun fetchExercises(@Header("Authorization")token:String):Response<List<Exercise>>

    @GET("/exercise-categories")
    suspend fun fetchExerciseCategories(@Header("Authorization") acccessToken: String): Response<List<ExerciseCategory>>

}

