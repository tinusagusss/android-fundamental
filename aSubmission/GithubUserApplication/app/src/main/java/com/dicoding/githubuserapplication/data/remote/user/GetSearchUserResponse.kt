package com.dicoding.githubuserapplication.data.remote.user

import com.dicoding.githubuserapplication.data.entity.User
import com.google.gson.annotations.SerializedName

data class GetSearchUserResponse(
    @field:SerializedName("total_count")
    val totalCount: Int,
    val items: List<User>
)
