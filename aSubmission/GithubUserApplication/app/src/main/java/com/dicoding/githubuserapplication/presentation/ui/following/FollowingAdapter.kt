package com.dicoding.githubuserapplication.presentation.ui.following

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.githubuserapplication.R
import com.dicoding.githubuserapplication.data.entity.User
import com.dicoding.githubuserapplication.databinding.UserItemRowBinding

class FollowingAdapter(private val followingList: List<User>) :
    RecyclerView.Adapter<FollowingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingAdapter.ViewHolder {
        val binding = UserItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingAdapter.ViewHolder, position: Int) {
        followingList[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = followingList.size

    inner class ViewHolder(private val binding: UserItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                txUsername.text = user.login
                Glide.with(imgAvatar.context)
                    .load(user.avatarUrl)
                    .error(R.color.shimmer_color)
                    .override(50, 50)
                    .placeholder(R.color.shimmer_color)
                    .into(imgAvatar)
            }
        }
    }

}