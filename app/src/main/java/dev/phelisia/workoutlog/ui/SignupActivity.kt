package dev.phelisia.workoutlog.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.phelisia.workoutlog.databinding.ActivitySignupBinding
import dev.phelisia.workoutlog.models.RegisterRequets
import dev.phelisia.workoutlog.models.RegisterResponse
import dev.phelisia.workoutlog.api.ApiClient
import dev.phelisia.workoutlog.api.ApiInterface
import dev.phelisia.workoutlog.models.LoginResponse
import dev.phelisia.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    val userViewModel: UserViewModel by viewModels()
    lateinit var sharedPrefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
        binding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSignup.setOnClickListener {
            validateSignup()

        }
        binding.tvSignin.setOnClickListener {
            val intent=Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }



    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { registerResponse->
            Toast.makeText(baseContext,registerResponse?.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(this@SignupActivity,LoginActivity::class.java))
        })
        userViewModel.loginErrorLiveData.observe(this, Observer { error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
    }



    fun validateSignup() {
        var name = binding.etFirstname.text.toString()
        var second = binding.etSecondname.text.toString()
        var phone = binding.etphonenumber.text.toString()
        var email = binding.etEmail.text.toString()
        var error = false
        if (name.isBlank()) {
            binding.tilFirstname.error = "Firstname required"
        }
        if (second.isBlank()) {
            binding.tilSecondname.error = "second name required"

        }
        if (phone.isBlank()) {
            binding.tilPhonenumber.error = "Phone number required"

        }
        if (email.isBlank()) {
            binding.tilEmail.error = "Email reuired"
        }


        var confirm = binding.etConfirmpassword.text.toString()
        var password = binding.etPassword.text.toString()
        if (confirm.isBlank()) {
            binding.tilConfirmpassword.error = "confirm password"
        } else {
            binding.tilConfirmpassword.error = null

        }
        if (password.isBlank()) {
            binding.tilPassword.error = "enter password"
        }
        if (confirm == password) {
            binding.btnSignup
        } else {
            binding.tilConfirmpassword.error = "invalid password"
        }
        if (!error) {
            val registerRequests = RegisterRequets(name, second, email, phone, password)
            userViewModel.registerUser(registerRequests)

        }


    }


        }


