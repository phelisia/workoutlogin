package dev.phelisia.workoutlog.repository

import androidx.lifecycle.LiveData
import dev.phelisia.workoutlog.WorkoutLog
import dev.phelisia.workoutlog.database.WorkoutDb
import dev.phelisia.workoutlog.models.WorkoutPlan
import dev.phelisia.workoutlog.models.WorkoutPlanItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutPlanRepository {
    val database=WorkoutDb.getDatabase(WorkoutLog.appContext)
    val workoutPlanDao=database.workoutPlanDao()
    val workoutPlanItemDao=database.workoutPlanItemDao()

     suspend fun saveWorkoutPlan(workoutPlan: WorkoutPlan){
        withContext(Dispatchers.IO){
            workoutPlanDao.insertworkoutPlan(workoutPlan)
        }
    }

    suspend fun saveWorkoutPlanItem(workoutPlanItem: WorkoutPlanItem){
        withContext(Dispatchers.IO){
            workoutPlanItemDao.insertworkoutPlanItem(workoutPlanItem)
        }
    }
    fun getWorkoutPlanByUserId(userId:String):LiveData<WorkoutPlan>{
        return  workoutPlanDao.getWorkoutPlanByUserId(userId)
    }
}