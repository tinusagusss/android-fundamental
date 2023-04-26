package com.dicoding.githubuserapplication.presentation.ui.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubuserapplication.data.entity.User
import com.dicoding.githubuserapplication.data.remote.ApiResponse
import com.dicoding.githubuserapplication.databinding.FragmentFollowingBinding
import com.dicoding.githubuserapplication.presentation.ui.detail.UserDetailActivity
import com.dicoding.githubuserapplication.presentation.ui.user.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowingFragment : Fragment() {

    private val userViewModel: UserViewModel by viewModels()

    private var _fragmentFollowingBinding: FragmentFollowingBinding? = null
    private val binding get() = _fragmentFollowingBinding!!

    private lateinit var followingAdapter: FollowingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentFollowingBinding = FragmentFollowingBinding.inflate(inflater)
        return _fragmentFollowingBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFollowing.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val username = (activity as UserDetailActivity).username
        getFollowingUser(username)
    }

    private fun getFollowingUser(login: String) {
        userViewModel.getFollowing(login).observe(viewLifecycleOwner) { response ->
            when (response) {
                is ApiResponse.Loading -> {
                    isLoading(true)
                }
                is ApiResponse.Success -> {
                    isLoading(false)
                    isEmpty(false)
                    followingAdapter = FollowingAdapter(response.data)
                    binding.rvFollowing.adapter = followingAdapter
                }
                is ApiResponse.Empty -> {
                    isLoading(false)
                    isEmpty(true)
                    val list = emptyList<User>()
                    followingAdapter = FollowingAdapter(list)
                    binding.rvFollowing.adapter = followingAdapter
                }
                is ApiResponse.Error -> {
                    isLoading(false)
                    Toast.makeText(context, response.errorMessage, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    isLoading(false)
                }
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

    private fun isLoading(loading: Boolean) {
        if (loading) {
            binding.shimmerLoading.visibility = View.VISIBLE
        } else {
            binding.shimmerLoading.visibility = View.INVISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentFollowingBinding = null
    }

}