package dev.phelisia.workoutlog.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "ExerciseCategories")
data class ExerciseCategory(
    @PrimaryKey @SerializedName ("category_id")var categoryId:String,
    @SerializedName ("category_name")var categoryNmae:String
)
