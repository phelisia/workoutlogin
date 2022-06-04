package dev.phelisia.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignupActivity : AppCompatActivity() {
    lateinit var tilFirstname:TextInputLayout
    lateinit var etFirstname:TextInputEditText
    lateinit var tilSecondname:TextInputLayout
    lateinit var etSecondname:TextInputEditText
    lateinit var tilEmail:TextInputLayout
    lateinit var etEmail:TextInputEditText
    lateinit var tilPassword:TextInputLayout
    lateinit var etPassword:TextInputEditText
    lateinit var tilConfirmPassword:TextInputLayout
    lateinit var etConfirmpassword:TextInputEditText
    lateinit var btnSignup:Button
    lateinit var tvSignin:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        tilFirstname=findViewById(R.id.tilFirstname)
        etFirstname=findViewById(R.id.etFirstname)
        tilSecondname=findViewById(R.id.tilSecondname)
        etSecondname=findViewById(R.id.etSecondname)
        tilEmail=findViewById(R.id.tilEmail)
        etEmail=findViewById(R.id.etEmail)
        tilPassword=findViewById(R.id.tilPassword)
        etPassword=findViewById(R.id.etPassword)
        tilConfirmPassword=findViewById(R.id.tilConfirmpassword)
        etConfirmpassword=findViewById(R.id.etConfirmpassword)
        tvSignin=findViewById(R.id.tvSignin)
        btnSignup=findViewById(R.id.btnSignup)
        btnSignup.setOnClickListener {
            validateSignup()
        }
        tvSignin.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }



    }
    fun validateSignup(){
        var name=etFirstname.text.toString()
        var second=etSecondname.text.toString()
        if (name.isBlank()){
            tilFirstname.error="Firstname required"
        }
        if (second.isBlank()){
            tilSecondname.error="second name required"

        }

        
        var confirm=etConfirmpassword.text.toString()
        var password=etPassword.text.toString()
        if(confirm.isBlank()){
            tilConfirmPassword.error="confirm password"
        }
        if (password.isBlank()){
            tilPassword.error="enter password"
        }
        if (confirm==password){
            btnSignup
        }
        else{
            tilConfirmPassword.error="invalid"
        }
    }
}