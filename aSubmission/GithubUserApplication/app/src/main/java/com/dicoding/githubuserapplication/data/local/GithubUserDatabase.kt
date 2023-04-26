package com.dicoding.githubuserapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.githubuserapplication.data.entity.Favorite
import com.dicoding.githubuserapplication.data.local.FavoriteDao

@Database(
    entities = [Favorite::class],
    version = 1,
    exportSchema = false
)
abstract class GithubUserDatabase: RoomDatabase() {

    abstract fun getUserDao(): FavoriteDao

}