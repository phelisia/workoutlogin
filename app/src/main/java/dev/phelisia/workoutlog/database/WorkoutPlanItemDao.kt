package dev.phelisia.workoutlog.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import dev.phelisia.workoutlog.models.WorkoutPlanItem

@Dao
interface WorkoutPlanItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertworkoutPlanItem(workoutPlanItem: WorkoutPlanItem)
}