package dev.phelisia.workoutlog.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName ("phone_number")var PhoneNumber :String,
    @SerializedName ("first_name")var firstName:String,
    @SerializedName ("last_number") var lastName:String,
    var email:String,
    @SerializedName ("user_id")var userId:String
)
