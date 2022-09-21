package dev.phelisia.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPrefs:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
        val accessToken=sharedPrefs.getString("ACCESS_TOKEN","")
        if (accessToken!!.isNotBlank()){
            val intent=Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()


        }
        else{
            val intent=Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
}