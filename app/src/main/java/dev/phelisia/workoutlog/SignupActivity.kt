package dev.phelisia.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.phelisia.workoutlog.databinding.ActivityLoginBinding
import dev.phelisia.workoutlog.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSignup.setOnClickListener {
            validateSignup()
        }
        binding.tvSignin.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }



    }
    fun validateSignup(){
        var name=binding.etFirstname.text.toString()
        var second=binding.etSecondname.text.toString()
        if (name.isBlank()){
            binding.tilFirstname.error="Firstname required"
        }
        if (second.isBlank()){
            binding.tilSecondname.error="second name required"

        }

        
        var confirm=binding.etConfirmpassword.text.toString()
        var password=binding.etPassword.text.toString()
        if (confirm.isBlank()) {
            binding.tilConfirmpassword.error = "confirm password"
        } else {
            binding.tilConfirmpassword.error = null
        }
        if (password.isBlank()){
            binding.tilPassword.error="enter password"
        }
        if (confirm==password){
            binding.btnSignup
        }
        else{
            binding.tilConfirmpassword.error = "invalid password"
        }
        }
    }
