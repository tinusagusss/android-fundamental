package com.dicoding.githubuserapplication.presentation.ui.detail

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.githubuserapplication.presentation.ui.followers.FollowerFragment
import com.dicoding.githubuserapplication.presentation.ui.following.FollowingFragment

class SectionPageAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = FollowingFragment()
            1 -> fragment = FollowerFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int = 2
}