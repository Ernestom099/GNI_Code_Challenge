package com.example.italikachallenges.ui.ratedmovies

import com.example.italikachallenges.models.Movie
import com.example.italikachallenges.services.MoviesService
import com.example.italikachallenges.utis.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RatedMoviesInteractor {
    fun getRatedMovies(ratedMoviesCallback: (List<Movie>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = MoviesService.create().getTopRatedMovies(Const.API_KEY_V3)
                ratedMoviesCallback(result.results)
            } catch (e: Exception) {
                ratedMoviesCallback(emptyList())
            }
        }
    }
}