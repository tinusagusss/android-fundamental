package com.dicoding.githubuserapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubuserapplication.data.entity.User
import com.dicoding.githubuserapplication.data.remote.ApiResponse
import com.dicoding.githubuserapplication.databinding.ActivityMainBinding
import com.dicoding.githubuserapplication.presentation.ui.detail.UserDetailActivity
import com.dicoding.githubuserapplication.presentation.ui.favorite.FavoriteActivity
import com.dicoding.githubuserapplication.presentation.ui.preferences.PreferencesActivity
import com.dicoding.githubuserapplication.presentation.ui.user.UserAdapter
import com.dicoding.githubuserapplication.presentation.ui.user.UserViewModel
import com.dicoding.githubuserapplication.presentation.ui.user.UserItemListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UserItemListener {

    private val userViewModel: UserViewModel by viewModels()

    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding!!

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_activityMainBinding?.root)

        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        )
        setupRv()

        binding.svUser.setQuery(binding.svUser.query, true)
        binding.svUser.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                getSearchUser(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.favorites -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
            }
            R.id.setting -> {
                startActivity(Intent(this, PreferencesActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRv() {
        binding.rvUser.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun getSearchUser(q: String) {
        userViewModel.getSearchUser(q).observe(this) { response ->
            when(response) {
                is ApiResponse.Loading -> {
                    isLoading(true)
                }
                is ApiResponse.Success -> {
                    isLoading(false)
                    isEmpty(false)
                    userAdapter = UserAdapter(response.data.items)
                    userAdapter.onItemClicked(this)
                    binding.rvUser.adapter = userAdapter
                }
                is ApiResponse.Empty -> {
                    isLoading(false)
                    isEmpty(true)
                    val emptyList = emptyList<User>()
                    userAdapter = UserAdapter(emptyList)
                    binding.rvUser.adapter = userAdapter
                }
                is ApiResponse.Error -> {
                    isLoading(false)
                    Toast.makeText(this, response.errorMessage, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    isLoading(false)
                    isEmpty(true)
                    Log.d("search_user", "Error unknown")
                }
            }
        }
    }

    private fun isLoading(loading: Boolean) {
        if (loading) {
            binding.apply {
                shimmerLoading.visibility = View.VISIBLE
                rvUser.visibility = View.INVISIBLE
                txEmpty.visibility = View.INVISIBLE
            }
        } else {
            binding.apply {
                rvUser.visibility = View.VISIBLE
                shimmerLoading.visibility = View.INVISIBLE
                txEmpty.visibility = View.INVISIBLE
            }
        }
    }

    private fun isEmpty(empty: Boolean) {
        if (empty) {
            binding.txEmpty.visibility = View.VISIBLE
        } else {
            binding.txEmpty.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }

    override fun onClicked(user: User) {
        val intent = Intent(this, UserDetailActivity::class.java)
        val loginUser = user.login
        intent.putExtra("user_login", loginUser)
        startActivity(intent)
    }

}