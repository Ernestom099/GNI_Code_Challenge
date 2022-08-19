package com.example.italikachallenges.ui.recommendations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.italikachallenges.models.Movie
import com.example.italikachallenges.ui.popularmovies.adapters.GridMoviesAdapter

class RecommendationsViewModel : ViewModel() {

    private val recommendationsInteractor by lazy { RecommendationsInteractor() }
    private val _movies: MutableLiveData<List<Movie>> by lazy { MutableLiveData() }
    val movies: LiveData<List<Movie>> = _movies

    fun getPopularMovies(movieId: Int) {
        recommendationsInteractor.getPopularMovies(movieId) { moviesResult ->
            _movies.postValue(moviesResult)

        }
    }

    fun getAdapter(): GridMoviesAdapter {
        return GridMoviesAdapter(_movies.value ?: emptyList(), { })
    }

    public override fun onCleared() {
        super.onCleared()
    }
}