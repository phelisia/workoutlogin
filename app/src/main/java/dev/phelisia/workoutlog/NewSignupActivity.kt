package dev.phelisia.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import dev.phelisia.workoutlog.databinding.ActivitySignupBinding

class NewSignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    private var isNameValid = false
    private var isEmailValid = false
    private var isPasswordValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        validateSignup()

        if (isValidSignUp()) {
            goToLoginScreen()
        }
    }

    private fun validateSignup() {
        validateName()
        validateEmail()
        validatePassword()
    }

    /**
     * Validate the first and second name
     */
    private fun validateName() {
        val name = binding.etFirstname.text.toString()
        val second = binding.etSecondname.text.toString()

        isNameValid = name.isBlank().not() && second.isBlank().not()

        if (name.isBlank()) {
            binding.tilFirstname.error = "First name required"
        } else {
            binding.tilFirstname.error = null
        }

        if (second.isBlank()) {
            binding.tilSecondname.error = "Second name required"
        } else {
            binding.tilSecondname.error = null
        }
    }

    /**
     * Validate the email address
     */
    private fun validateEmail() {
        if (binding.etEmail.text.toString().isBlank()) {
            binding.tilEmail.error = "invalid email"
            isEmailValid = false
        } else {
            binding.tilEmail.error = null
            isEmailValid = true
        }
    }

    /**
     * Validate the password
     */
    private fun validatePassword() {
        val confirm = binding.etConfirmpassword.text.toString()
        val password = binding.etPassword.text.toString()

        if (confirm.isBlank()) {
            binding.tilConfirmpassword.error = "confirm password"
        } else {
            binding.tilConfirmpassword.error = null
        }

        if (password.isBlank()) {
            binding.tilPassword.error = "enter password"
        } else {
            binding.tilPassword.error = null
        }

        if (confirm == password && confirm.isBlank().not() && password.isBlank().not()) {
            binding.tilConfirmpassword.error = null
            isPasswordValid = true
        } else {
            isPasswordValid = false
            binding.tilConfirmpassword.error = "invalid"
        }
    }

    private fun isValidSignUp(): Boolean {
        return isNameValid && isEmailValid && isPasswordValid
    }

    private fun goToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
