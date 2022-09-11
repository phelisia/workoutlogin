package dev.phelisia.workoutlog.api

import dev.phelisia.workoutlog.models.LoginRequest
import dev.phelisia.workoutlog.models.LoginResponse
import dev.phelisia.workoutlog.models.RegisterRequets
import dev.phelisia.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
     suspend fun registerUser(@Body registerRequest: RegisterRequets):Response<RegisterResponse>

    @POST ("/login")
    suspend fun login(@Body loginRequest: LoginRequest):Response<LoginResponse>

}

