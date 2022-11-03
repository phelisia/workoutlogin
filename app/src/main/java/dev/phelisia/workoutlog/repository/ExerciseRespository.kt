package dev.phelisia.workoutlog.repository

import androidx.lifecycle.LiveData
import dev.phelisia.workoutlog.WorkoutLog
import dev.phelisia.workoutlog.api.ApiClient
import dev.phelisia.workoutlog.api.ApiInterface
import dev.phelisia.workoutlog.database.WorkoutDb
import dev.phelisia.workoutlog.models.Exercise
import dev.phelisia.workoutlog.models.ExerciseCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ExerciseRespository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
    val database = WorkoutDb.getDatabase(WorkoutLog.appContext)
    val exerciseCategoryDao = database.exerciseCategoryDao()
    val exerciseDao = database.exerciseDao()
    suspend fun fetchExercisesCategories(accessToken: String): Response<List<ExerciseCategory>> {

        return withContext(Dispatchers.IO) {

            var response = apiClient.fetchExerciseCategories(accessToken)
            if (response.isSuccessful) {
                var categories = response.body()
                categories?.forEach { category ->
                    exerciseCategoryDao.insertExerciseCategory(category)
                }
            }
            return@withContext response
        }
    }


    fun getDbExerciseCategories(): LiveData<List<ExerciseCategory>>{
        return exerciseCategoryDao.getExerciseCategory()
    }
    suspend fun fetchApiExercises(accessToken: String){
        withContext(Dispatchers.IO){
            val response = apiClient.fetchExercises(accessToken)
            if (response.isSuccessful){
                val exercises = response.body()
                exercises?.forEach { exercise ->
                    exerciseDao.insertExercise(exercise)
                }
            }
            return@withContext response
        }
    }

    fun getDbExercises():LiveData<List<Exercise>>{
        return exerciseDao.getExercises()
    }
    fun getDbCategories():LiveData<List<ExerciseCategory>>{
        return  exerciseCategoryDao.getExerciseCategory()
    }
    fun getExercisesbycategoryId(categoryId: String):LiveData<List<Exercise>>{
        return  exerciseDao.getExercisesByCategoryId(categoryId)

    }
}



