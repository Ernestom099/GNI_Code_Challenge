package com.example.italikachallenges.ui.recommendations

import com.example.italikachallenges.injections.DaggerApplicationComponent
import com.example.italikachallenges.models.Movie
import com.example.italikachallenges.services.MoviesService
import com.example.italikachallenges.utis.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecommendationsInteractor {

    init {
        DaggerApplicationComponent.create().inject(this)
    }

    @Inject
    lateinit var moviesService: MoviesService

    fun getPopularMovies(movieId: Int, popularMoviesCallback: (List<Movie>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = moviesService.getRecomendations(movieId, Const.API_KEY_V3)
                popularMoviesCallback(result.results)
            } catch (e: Exception) {
                popularMoviesCallback(emptyList())
            }
        }
    }
}