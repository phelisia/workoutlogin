package dev.phelisia.workoutlog.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dev.phelisia.workoutlog.R
import dev.phelisia.workoutlog.Util.Constants
import dev.phelisia.workoutlog.databinding.ActivityHomeBinding
import dev.phelisia.workoutlog.databinding.FragmentPlanBinding
import dev.phelisia.workoutlog.models.Exercise
import dev.phelisia.workoutlog.models.WorkoutPlan
import dev.phelisia.workoutlog.viewmodel.ExerciseViewModel
import dev.phelisia.workoutlog.viewmodel.WorkoutPlanViewModel
import java.util.UUID


class PlanFragment : Fragment() {
    var binding: FragmentPlanBinding? = null
    val exerciseViewModel: ExerciseViewModel by viewModels()
    val workoutPlanViewModel: WorkoutPlanViewModel by viewModels()
    lateinit var  workoutPlanId:String


    val bind get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlanBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onResume() {
        super.onResume()
        setupDaySpinner()
        setUpCategorySpinner()
        exerciseViewModel.getDbCategories()
        setUpExerciseSpinner()
        checkForExistingWorkoutPlan()
        bind.btnadditem.setOnClickListener { clickAddItem() }
        bind.btnsaveday.setOnClickListener { clickSaveDay() }



    }

    fun setupDaySpinner() {
        val days =
            listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sundays")
        val daysAdapter =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item)
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bind.spday.adapter = daysAdapter

    }

    fun setUpCategorySpinner() {
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { categories ->
            val categoryAdapter = CategoryAdapter(requireContext(), categories)
            bind.spcategory.adapter = categoryAdapter
            bind.spcategory.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val selectedCategory = categories.get(p2)
                    val categoryId = selectedCategory.categoryId
                    exerciseViewModel.getExerciseByCategoryId(categoryId)
                    setUpExerciseSpinner()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        })
        fun setupExerciseSpinner() {
            exerciseViewModel.exerciseLiveData.observe(this, Observer { exercises ->
                val exerciseAdapter = ExerciseAdapter(requireContext(), exercises)
                bind.spexercises.adapter = exerciseAdapter
            })
        }

    }

    fun setUpExerciseSpinner() {
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { categories ->
            val categoryAdapter = CategoryAdapter(requireContext(), categories)
            bind.spexercises.adapter = categoryAdapter
        })
    }

    fun clickAddItem() {
        var error = false
        if (bind.spday.selectedItemPosition == 0) {
            error = true
            Toast.makeText(requireContext(), "Select Day", Toast.LENGTH_SHORT).show()
        }
        if (bind.spcategory.selectedItemPosition == 0) {
            error = true
            Toast.makeText(requireContext(), "Select Category", Toast.LENGTH_SHORT).show()
        }
        if (bind.spexercises.selectedItemPosition == 0) {
            error = true
            Toast.makeText(requireContext(), "Select Exercise", Toast.LENGTH_SHORT).show()
        }
        if (!error) {
            val selectedExercise = bind.spexercises.selectedItem as Exercise
            workoutPlanViewModel.selectedExercisesIds.add(selectedExercise.exerciseId)
            bind.spexercises.setSelection(0)
            bind.spcategory.setSelection(0)
        }
    }


    fun checkForExistingWorkoutPlan(){
        val prefs = requireActivity().getSharedPreferences(Constants.prefsFile, Context.MODE_PRIVATE)
        val userId = prefs.getString(Constants.userId, "").toString()
        workoutPlanViewModel.getExistingWorkoutPlan(userId)
        workoutPlanViewModel.workoutPlanLiveData.observe(this, Observer { workoutPlan->
            if (workoutPlan==null){
                val newWorkoutPlan = WorkoutPlan(UUID.randomUUID().toString(), userId)
                workoutPlanViewModel.saveWorkoutPlan(newWorkoutPlan)
            }else{
                workoutPlanId=workoutPlan.workoutPlanId
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    fun getDayNumber(day:String):Int?{
        val dayMap=HashMap<String,Int>()
        dayMap.put("Monday",1)
        dayMap.put("Tuesday",2)
        dayMap.put("Wednesday",3)
        dayMap.put("Thursday",4)
        dayMap.put("Friday",5)
        dayMap.put("Saturday",6)
        dayMap.put("Sunday",7)
        return  dayMap.get(day)?:-1
    }
    fun clickSaveDay(){
        if (bind.spday.selectedItemPosition==0){
            Toast.makeText(requireContext(),"selected day",Toast.LENGTH_LONG).show()
            return

        }
        val day=bind.spday.selectedItem.toString()
        val dayNumber=getDayNumber(day)

        if (  workoutPlanViewModel.selectedExercisesIds.isEmpty()){
            Toast.makeText(requireContext(),"Select some exercises for the day $day",Toast.LENGTH_LONG).show()
            return
        }
        if (dayNumber !=null){
                workoutPlanViewModel.createWorkoutPlanItem(dayNumber,workoutPlanId)
        }

        bind.spday.setSelection(0)


    }
}







