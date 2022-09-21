package dev.phelisia.workoutlog.models

import com.google.gson.annotations.SerializedName

data class ExerciseCategory(
    @SerializedName ("category_id")var categoryId:String,
    @SerializedName ("category_name")var categoryNmae:String
)
