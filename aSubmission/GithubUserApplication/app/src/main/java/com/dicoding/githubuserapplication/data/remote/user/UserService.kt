package com.dicoding.githubuserapplication.data.remote.user

import com.dicoding.githubuserapplication.data.entity.User
import retrofit2.http.*

interface UserService {

    @GET("search/users")
    @Headers("Authorization: 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")
    suspend fun getSearchUsers(
        @Query("q") query: String
    ) : GetSearchUserResponse

    @GET("users/{login}")
    @Headers("Authorization: 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")
    suspend fun getDetailUser(
        @Path("login") login: String
    ) : User

    @GET("users/{login}/followers")
    @Headers("Authorization: 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")
    suspend fun getFollowers(
        @Path("login") login: String,
    ) : List<User>

    @GET("users/{login}/following")
    @Headers("Authorization: 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")
    suspend fun getFollowing(
        @Path("login") login: String
    ) : List<User>

}