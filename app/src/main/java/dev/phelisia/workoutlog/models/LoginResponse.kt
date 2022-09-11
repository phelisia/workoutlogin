package dev.phelisia.workoutlog.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    var message:String,
    @SerializedName("access_token")var accessToken:String,
    @SerializedName("user_id")var UserId:String,
    @SerializedName("profile_id")var ProfileId:String,

    )
