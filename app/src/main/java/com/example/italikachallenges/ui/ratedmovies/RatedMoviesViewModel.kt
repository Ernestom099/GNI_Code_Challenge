package com.example.italikachallenges.ui.ratedmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.italikachallenges.models.Movie

class RatedMoviesViewModel : ViewModel() {

    private val ratedMoviesInteractor by lazy { RatedMoviesInteractor() }
    private val _movies: MutableLiveData<List<Movie>> by lazy { MutableLiveData() }
    val movies: LiveData<List<Movie>> = _movies

    fun getPopularMovies() {
        ratedMoviesInteractor.getRatedMovies { moviesResult ->
            _movies.postValue(moviesResult)
        }
    }

    public override fun onCleared() {
        super.onCleared()
    }
}