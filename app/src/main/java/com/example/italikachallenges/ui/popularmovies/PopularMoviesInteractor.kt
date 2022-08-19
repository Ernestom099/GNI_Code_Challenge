package com.example.italikachallenges.ui.popularmovies

import android.util.Log
import com.example.italikachallenges.models.Movie
import com.example.italikachallenges.models.People
import com.example.italikachallenges.services.MoviesService
import com.example.italikachallenges.services.PeopleService
import com.example.italikachallenges.utis.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopularMoviesInteractor {
    fun getPopularMovies(popularMoviesCallback: (List<Movie>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = MoviesService.create().getPopularMovies(Const.API_KEY_V3)
                popularMoviesCallback(result.results)
            } catch (e: Exception) {
                popularMoviesCallback(emptyList())
            }
        }
    }
}