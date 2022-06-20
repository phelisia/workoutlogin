package dev.phelisia.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.phelisia.workoutlog.databinding.ActivityHomeBinding
import dev.phelisia.workoutlog.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

       binding.btnLogin.setOnClickListener {
            validateLogin()
             finish()




        }

        binding.tvSignup.setOnClickListener {
            val intent=Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }

    }
    fun validateLogin(){
        var email= binding.etEmail.text.toString()
        var password=binding.etPassword.text.toString()
        var error=false
        if (email.isBlank()){
            binding.tilEmail.error = "password required"
            error=true
        }
        if (password.isBlank()) {
           binding.tilPassword.error = "password required"
            error=true
        }

        if(!error){
            startActivity(Intent(this,HomeActivity::class.java))


        }

        }
    }
