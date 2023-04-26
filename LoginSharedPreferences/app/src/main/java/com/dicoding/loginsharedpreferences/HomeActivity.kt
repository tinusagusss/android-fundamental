package com.dicoding.loginsharedpreferences

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.loginsharedpreferences.databinding.ActivityHomeBinding
import com.dicoding.loginsharedpreferences.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)

        val username = sharedPreferences.getString("USERNAME", "").toString()
        val password = sharedPreferences.getString("PASSWORD", "").toString()

        binding.tvDisplay.text = "Username: $username and Password $password"

        binding.btnLogout.setOnClickListener {

            val editor = sharedPreferences.edit()
            editor.putString("USERNAME", "")
            editor.putString("PASSWORD", "")
            editor.apply()

            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}