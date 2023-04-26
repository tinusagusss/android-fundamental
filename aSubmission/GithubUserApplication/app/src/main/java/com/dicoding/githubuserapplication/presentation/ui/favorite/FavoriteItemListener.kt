package com.dicoding.githubuserapplication.presentation.ui.favorite

import com.dicoding.githubuserapplication.data.entity.Favorite

interface FavoriteItemListener {

    fun onItemClicked(favorite: Favorite)

}