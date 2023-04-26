package com.dicoding.githubuserapplication.data.repository

import com.dicoding.githubuserapplication.data.entity.User
import com.dicoding.githubuserapplication.data.remote.ApiResponse
import com.dicoding.githubuserapplication.data.remote.user.GetSearchUserResponse
import com.dicoding.githubuserapplication.data.source.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userDataSource: UserDataSource)  {

    suspend fun getSearchUser(query: String): Flow<ApiResponse<GetSearchUserResponse>> {
        return userDataSource.getSearchUsers(query).flowOn(Dispatchers.IO)
    }

    suspend fun getDetailUser(login: String): Flow<ApiResponse<User>> {
        return userDataSource.getDetailUser(login).flowOn(Dispatchers.IO)
    }

    suspend fun getFollowers(login: String): Flow<ApiResponse<List<User>>> {
        return userDataSource.getFollowers(login).flowOn(Dispatchers.IO)
    }

    suspend fun getFollowing(login: String): Flow<ApiResponse<List<User>>> {
        return userDataSource.getFollowing(login).flowOn(Dispatchers.IO)
    }

}