package dev.phelisia.workoutlog.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.phelisia.workoutlog.R
import dev.phelisia.workoutlog.Util.Constants
import dev.phelisia.workoutlog.databinding.ActivityHomeBinding
import dev.phelisia.workoutlog.models.LoginResponse
import dev.phelisia.workoutlog.viewmodel.ExerciseViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPrefs:SharedPreferences
    val exerciseViewModel:ExerciseViewModel by viewModels()
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castViews()
        setBottomNav()
        sharedPref=getSharedPreferences(Constants.prefsFile, MODE_PRIVATE)
        val token=sharedPref.getString(Constants.accessToken,Constants.EMPTY_STRING)
        exerciseViewModel.fetchExerciseCategory(token!!)
        binding.tvlogout.setOnClickListener {
            val editor=sharedPrefs.edit()
            editor.putString("ACCESS_TOKEN","")
            editor.putString("USER_ID","")
            editor.putString("PROFILE_ID","")
            editor.apply()
            startActivity(Intent(this,LoginResponse::class.java))
        }


    }

    override fun onResume() {
        super.onResume()
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { exerciseCategories->
            Toast.makeText(this,"fetched ${exerciseCategories.size} categories",Toast.LENGTH_LONG).show()
        })
        exerciseViewModel.errorLiveData.observe(this, Observer { errorMsg->
            Toast.makeText(this,errorMsg,Toast.LENGTH_LONG).show()
        })
    }
    fun castViews() {
        binding.fcvHome
        binding.bnvHome
    }

    fun setBottomNav() {
        binding.bnvHome.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.plan -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fcvHome,
                        PlanFragment()
                    ).commit()
                    true
                }
                R.id.track -> {
                    val transaction = supportFragmentManager.beginTransaction().replace(
                        R.id.fcvHome,
                        TrackFragment()
                    ).commit()
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fcvHome,
                        ProfileFragment()
                    ).commit()
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}