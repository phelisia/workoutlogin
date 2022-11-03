package dev.phelisia.workoutlog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.phelisia.workoutlog.models.Exercise
import dev.phelisia.workoutlog.models.ExerciseCategory
import dev.phelisia.workoutlog.repository.ExerciseRespository
import kotlinx.coroutines.launch

class ExerciseViewModel :ViewModel(){
    val exerciseRespository=ExerciseRespository()
    lateinit var exerciseCategoryLiveData : LiveData<List<ExerciseCategory>>
    lateinit var  exerciseLiveData:LiveData<List<Exercise>>
    val errorLiveData= MutableLiveData<String?>()

    fun fetchExerciseCategory(accessToken:String){
        viewModelScope.launch {
            val response=exerciseRespository.fetchExercisesCategories(accessToken)
            if(!response.isSuccessful){
                errorLiveData.postValue(response.errorBody()?.string())
            }
            else{
                val errorMsg=response.errorBody()?.string()
                errorLiveData.postValue(errorMsg)
            }
        }

    }
    fun getDbExerciseCategories(){
        exerciseCategoryLiveData = exerciseRespository.getDbExerciseCategories()
    }
    fun getDbCategories(){
        exerciseCategoryLiveData=exerciseRespository.getDbCategories()
    }

    fun fetchApiExercises(accessToken: String) {
        viewModelScope.launch {
            exerciseRespository.fetchApiExercises(accessToken)
        }
    }

    fun getDbExercises(){
        exerciseLiveData = exerciseRespository.getDbExercises()
    }

    fun getExerciseByCategoryId(categoryId: String){
        exerciseLiveData = exerciseRespository.getExercisesbycategoryId(categoryId)
    }
}