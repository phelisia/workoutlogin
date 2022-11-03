package dev.phelisia.workoutlog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.phelisia.workoutlog.models.WorkoutPlan
import dev.phelisia.workoutlog.models.WorkoutPlanItem
import dev.phelisia.workoutlog.repository.WorkoutPlanRepository
import kotlinx.coroutines.launch
import java.util.UUID

class WorkoutPlanViewModel : ViewModel(){
    val workoutPlanRepository=WorkoutPlanRepository()
    lateinit var  workoutPlanLiveData: LiveData<WorkoutPlan>
    var selectedExercisesIds= mutableListOf<String>()

    fun saveWorkoutPlan(workoutPlan: WorkoutPlan){
        viewModelScope.launch {
        workoutPlanRepository.saveWorkoutPlan(workoutPlan)}
    }
    fun getExistingWorkoutPlan(userId:String){
        workoutPlanLiveData=workoutPlanRepository.getWorkoutPlanByUserId(userId)


    }

    fun createWorkoutPlanItem(dayNumber:Int,workoutPlanId:String){
        val workoutPlanItem=WorkoutPlanItem(workoutPlanItemId = UUID.randomUUID().toString(),workoutPlanId=workoutPlanId,
        day = dayNumber, exercisesIds = selectedExercisesIds)
        viewModelScope.launch {
            workoutPlanRepository.saveWorkoutPlanItem(workoutPlanItem)
        }

    }
}