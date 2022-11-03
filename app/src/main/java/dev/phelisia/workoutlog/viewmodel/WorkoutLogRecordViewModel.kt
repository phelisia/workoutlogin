package dev.phelisia.workoutlog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.phelisia.workoutlog.models.WorkoutLogRecord
import dev.phelisia.workoutlog.repository.WorkoutLogRecordRepositiory
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class WorkoutLogRecordViewModel : ViewModel(){
     val workoutlogRepository=WorkoutLogRecordRepositiory()
    lateinit var todayRecordsLiveData: LiveData<List<WorkoutLogRecord>>

    fun saveWorkoutLog(workoutLogRecord: WorkoutLogRecord){
        viewModelScope.launch{
            workoutlogRepository.saveWorkoutLogRecord(workoutLogRecord)
        }
    }

    fun getTodayWorkoutRecords(userId:String){
        todayRecordsLiveData=workoutlogRepository.getTodaysWorkoutLogRecords(userId,getCurrentDate())

    }
    fun getCurrentDate():String{
        val formatter=SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH)
        return  formatter.format(Date())

    }


}