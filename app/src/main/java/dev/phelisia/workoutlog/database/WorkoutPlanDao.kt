package dev.phelisia.workoutlog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.phelisia.workoutlog.models.WorkoutPlan

@Dao
interface WorkoutPlanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertworkoutPlan(workoutPlan: WorkoutPlan)

    @Query("SELECT * FROM workoutplan WHERE userId =:userId")
    fun getWorkoutPlanByUserId(userId:String):LiveData<WorkoutPlan>

}