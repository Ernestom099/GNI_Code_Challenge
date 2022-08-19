package com.example.italikachallenges.ui.movies.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app. FragmentPagerAdapter
import com.example.italikachallenges.ui.ratedmovies.TopRatedMoviesFragment
import com.example.italikachallenges.ui.popularmovies.MostPopularMoviesFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private val mostPopularMoviesFragment by lazy {
        MostPopularMoviesFragment()
    }

    private val topRatedMoviesFragment by lazy {
        TopRatedMoviesFragment()
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MostPopularMoviesFragment()
            }
            1 -> {
                TopRatedMoviesFragment()
            }
            else -> MostPopularMoviesFragment()
        }
    }


    override fun getCount(): Int {
        return 2
    }
}