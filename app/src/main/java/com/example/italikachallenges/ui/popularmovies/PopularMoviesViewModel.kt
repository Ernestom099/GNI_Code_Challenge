package com.example.italikachallenges.ui.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.italikachallenges.injections.DaggerApplicationComponent
import com.example.italikachallenges.models.Movie
import com.example.italikachallenges.models.MovieResponse
import javax.inject.Inject

class PopularMoviesViewModel : ViewModel() {
    init {
        DaggerApplicationComponent.create().inject(this)
    }

    @Inject
    lateinit var popularMoviesInteractor:PopularMoviesInteractor

    private val _movies: MutableLiveData<MovieResponse> by lazy { MutableLiveData() }
    val movies: LiveData<MovieResponse> = _movies

    fun getPopularMovies() {
        popularMoviesInteractor.getPopularMovies { moviesResult ->
            _movies.postValue(moviesResult)

        }
    }

    public override fun onCleared() {
        super.onCleared()
    }
}