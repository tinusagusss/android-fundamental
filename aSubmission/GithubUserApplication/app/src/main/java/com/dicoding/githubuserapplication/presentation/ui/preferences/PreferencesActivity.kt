package com.dicoding.githubuserapplication.presentation.ui.preferences

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.dicoding.githubuserapplication.MainViewModel
import com.dicoding.githubuserapplication.R
import com.dicoding.githubuserapplication.databinding.ActivityPreferencesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreferencesActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private var _activityPreferencesBinding: ActivityPreferencesBinding? = null
    private val binding get() = _activityPreferencesBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityPreferencesBinding = ActivityPreferencesBinding.inflate(layoutInflater)
        setContentView(_activityPreferencesBinding?.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = resources.getString(R.string.preferences)

        switchChecked()

        binding.switchDarkMode.setOnCheckedChangeListener { _, checked ->
            mainViewModel.saveThemeSetting(checked)
        }
    }

    private fun switchChecked() {
        mainViewModel.getThemePreferences().observe(this) { isDarkMode ->
            if (isDarkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchDarkMode.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchDarkMode.isChecked = false
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}