package com.dicoding.githubuserapplication.data.source

import com.dicoding.githubuserapplication.data.entity.Favorite
import com.dicoding.githubuserapplication.data.entity.User
import com.dicoding.githubuserapplication.data.local.FavoriteDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteDataSource @Inject constructor(private val favoriteDao: FavoriteDao) {

    suspend fun addFavorites(user: User) {
        val favoriteData = Favorite(
            login = user.login,
            name = user.name,
            avatarUrl = user.avatarUrl,
            company = user.company,
            location = user.location,
            followers = user.followers,
            following = user.following,
            publicRepos = user.publicRepos
        )

        favoriteDao.addFavoriteUser(favoriteData)
    }

    suspend fun deleteFavorite(username: String) {
        favoriteDao.removeFavorite(username)
    }

    suspend fun getAllFavorites(): List<Favorite> = favoriteDao.getAllFavorites()

    suspend fun isUserExist(login: String): Boolean = favoriteDao.isUserExist(login)

}