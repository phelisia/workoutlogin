package dev.phelisia.workoutlog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.phelisia.workoutlog.models.Exercise

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(exercise: Exercise)

    @Query("SELECT * FROM Exercises")
    fun getExercises():LiveData<List<Exercise>>


    @Query("SELECT * FROM Exercises WHERE categoryId=:categoryId")
    fun fetchExerciseCategory(categoryId:String):LiveData<List<Exercise>>

    @Query("SELECT * FROM Exercises WHERE categoryId=:selectedCategoryId")

    fun getExercisesByCategoryId(selectedCategoryId:String):LiveData<List<Exercise>>



}