package com.dicoding.githubuserapplication.presentation.di

import android.content.Context
import androidx.room.Room
import com.dicoding.githubuserapplication.data.local.FavoriteDao
import com.dicoding.githubuserapplication.data.local.GithubUserDatabase
import com.dicoding.githubuserapplication.utils.global.ConstVal.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GithubUserDatabase {
        return Room.databaseBuilder(context, GithubUserDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(database: GithubUserDatabase): FavoriteDao = database.getUserDao()

}