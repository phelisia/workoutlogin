package dev.phelisia.workoutlog.repository

import androidx.lifecycle.LiveData
import dev.phelisia.workoutlog.WorkoutLog
import dev.phelisia.workoutlog.database.WorkoutDb
import dev.phelisia.workoutlog.models.WorkoutLogRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date

class WorkoutLogRecordRepositiory {
    val database=WorkoutDb.getDatabase(WorkoutLog.appContext)
    val workoutLogRecordDao=database.workoutLogRecordDao()

   suspend fun saveWorkoutLogRecord(workoutLogRecord: WorkoutLogRecord){
        withContext(Dispatchers.IO){
            workoutLogRecordDao.insertWorkoutLogRecord(workoutLogRecord)
        }
    }
  fun getTodaysWorkoutLogRecords(userId:String,currentDate: String): LiveData<List<WorkoutLogRecord>> {
      return workoutLogRecordDao.getWorkoutLogByUserId(userId,currentDate)
  }
}