package dev.phelisia.workoutlog.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class WorkoutLogRecord(
    @PrimaryKey var workoutlogId:String,
    var date:String,
    var exerciseId:String,
    var set:Int,
    var weight:Int?,
    var workoutPlanItem:String,
    var userId:String
)
