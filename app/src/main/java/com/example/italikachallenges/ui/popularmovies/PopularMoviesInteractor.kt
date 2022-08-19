package com.example.italikachallenges.ui.popularmovies

import android.util.Log
import com.example.italikachallenges.injections.DaggerApplicationComponent
import com.example.italikachallenges.models.Movie
import com.example.italikachallenges.models.MovieResponse
import com.example.italikachallenges.models.People
import com.example.italikachallenges.services.MoviesService
import com.example.italikachallenges.services.PeopleService
import com.example.italikachallenges.utis.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularMoviesInteractor @Inject constructor(){

   init {
       DaggerApplicationComponent.create().inject(this)
   }

    @Inject
    lateinit var moviesService: MoviesService

    fun getPopularMovies(popularMoviesCallback: (MovieResponse) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result =moviesService.getPopularMovies(Const.API_KEY_V3)
                popularMoviesCallback(MovieResponse(true,result.results))
            } catch (e: Exception) {
                popularMoviesCallback(MovieResponse(false, emptyList()))
            }
        }
    }
}