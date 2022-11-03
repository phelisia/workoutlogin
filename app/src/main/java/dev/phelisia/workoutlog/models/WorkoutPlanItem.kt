package dev.phelisia.workoutlog.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "WorkoutPLanItems", indices = [Index(value=["workoutPlanId","day"], unique = true)])
data class WorkoutPlanItem(
   @PrimaryKey var workoutPlanItemId:String,
    var day:Int,
    var exercisesIds:List<String>,
    var workoutPlanId:String
)
