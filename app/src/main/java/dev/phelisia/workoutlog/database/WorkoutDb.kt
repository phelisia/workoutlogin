package dev.phelisia.workoutlog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.phelisia.workoutlog.models.Exercise
import dev.phelisia.workoutlog.models.ExerciseCategory
import dev.phelisia.workoutlog.models.WorkoutPlan
import dev.phelisia.workoutlog.models.WorkoutPlanItem

@Database(entities = arrayOf(ExerciseCategory::class,Exercise::class,WorkoutPlan::class,WorkoutPlanItem::class,WorkoutLogRecordDao::class), version = 6)
@TypeConverters(Converters::class)
 abstract class WorkoutDb :RoomDatabase (){
    abstract fun exerciseCategoryDao(): ExerciseCategoryDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutPlanDao():WorkoutPlanDao
    abstract fun workoutPlanItemDao():WorkoutPlanItemDao
    abstract  fun workoutLogRecordDao():WorkoutLogRecordDao
     companion object {
         private var database: WorkoutDb? = null
         fun getDatabase(context: Context): WorkoutDb{
             if (database == null) {
                 database = Room.databaseBuilder(context,
                     WorkoutDb::class.java, "WorkoutDb")
                     .fallbackToDestructiveMigration().build()
             }
             return database as WorkoutDb
         }
     }

}