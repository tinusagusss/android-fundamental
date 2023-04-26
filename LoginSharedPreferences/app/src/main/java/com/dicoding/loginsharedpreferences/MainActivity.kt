package com.dicoding.loginsharedpreferences

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.loginsharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val getUsername = sharedPreferences.getString("USERNAME", "")
        val getPassword = sharedPreferences.getString("PASSWORD", "")

        if (!getUsername.equals("") && !getPassword.equals(""))
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))


        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()

            val editor = sharedPreferences.edit()
            editor.putString("USERNAME", username)
            editor.putString("PASSWORD", password)
            editor.apply()

            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
        }
    }
}