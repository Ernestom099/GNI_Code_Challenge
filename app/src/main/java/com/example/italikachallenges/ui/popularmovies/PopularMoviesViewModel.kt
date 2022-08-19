package com.example.italikachallenges.ui.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.italikachallenges.models.Movie

class PopularMoviesViewModel : ViewModel() {

    private val popularMoviesInteractor by lazy { PopularMoviesInteractor() }
    private val _movies: MutableLiveData<List<Movie>> by lazy { MutableLiveData() }
    val movies: LiveData<List<Movie>> = _movies

    fun getPopularMovies() {
        popularMoviesInteractor.getPopularMovies { moviesResult ->
            _movies.postValue(moviesResult)

        }
    }

    public override fun onCleared() {
        super.onCleared()
    }
}