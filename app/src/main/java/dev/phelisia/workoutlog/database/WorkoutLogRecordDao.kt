package dev.phelisia.workoutlog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.phelisia.workoutlog.models.WorkoutLogRecord
import java.util.*

@Dao
interface WorkoutLogRecordDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertWorkoutLogRecord(workoutLog: WorkoutLogRecord)

    @Query("SELECT * FROM  workoutLogRecord WHERE userId=:userId AND date >= :currentDate")
    fun getWorkoutLogByUserId(userId:String,currentDate: String): LiveData<List<WorkoutLogRecord>>
}