package com.example.italikachallenges.ui.ratedmovies

import com.example.italikachallenges.injections.DaggerApplicationComponent
import com.example.italikachallenges.models.Movie
import com.example.italikachallenges.models.MovieResponse
import com.example.italikachallenges.services.MoviesService
import com.example.italikachallenges.utis.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RatedMoviesInteractor @Inject constructor(){

    init {
        DaggerApplicationComponent.create().inject(this)
    }

    @Inject
    lateinit var moviesService: MoviesService

    fun getRatedMovies(ratedMoviesCallback: (MovieResponse) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = moviesService.getTopRatedMovies(Const.API_KEY_V3)
                ratedMoviesCallback(MovieResponse(true,result.results))
            } catch (e: Exception) {
                ratedMoviesCallback(MovieResponse(false, emptyList()))
            }
        }
    }
}