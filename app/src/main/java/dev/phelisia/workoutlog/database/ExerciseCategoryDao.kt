package dev.phelisia.workoutlog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.phelisia.workoutlog.models.ExerciseCategory

@Dao
interface ExerciseCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExerciseCategory(exerciseCategory: ExerciseCategory)


    @Query("SELECT * FROM exercisecategories")
    fun getExerciseCategory():LiveData<List<ExerciseCategory>>
}