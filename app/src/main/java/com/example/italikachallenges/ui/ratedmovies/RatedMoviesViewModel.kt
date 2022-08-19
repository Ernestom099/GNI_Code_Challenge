package com.example.italikachallenges.ui.ratedmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.italikachallenges.injections.DaggerApplicationComponent
import com.example.italikachallenges.models.Movie
import com.example.italikachallenges.models.MovieResponse
import javax.inject.Inject

class RatedMoviesViewModel : ViewModel() {
    init {
        DaggerApplicationComponent.create().inject(this)
    }

    @Inject
    lateinit var ratedMoviesInteractor :RatedMoviesInteractor
    private val _movies: MutableLiveData<MovieResponse> by lazy { MutableLiveData() }
    val movies: LiveData<MovieResponse> = _movies

    fun getPopularMovies() {
        ratedMoviesInteractor.getRatedMovies { moviesResult ->
            _movies.postValue(moviesResult)
        }
    }

    public override fun onCleared() {
        super.onCleared()
    }
}