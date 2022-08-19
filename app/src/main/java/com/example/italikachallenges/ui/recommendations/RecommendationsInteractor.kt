package com.example.italikachallenges.ui.recommendations

import com.example.italikachallenges.models.Movie
import com.example.italikachallenges.services.MoviesService
import com.example.italikachallenges.utis.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecommendationsInteractor {
    fun getPopularMovies(movieId: Int, popularMoviesCallback: (List<Movie>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = MoviesService.create().getRecomendations(movieId, Const.API_KEY_V3)
                popularMoviesCallback(result.results)
            } catch (e: Exception) {
                popularMoviesCallback(emptyList())
            }
        }
    }
}