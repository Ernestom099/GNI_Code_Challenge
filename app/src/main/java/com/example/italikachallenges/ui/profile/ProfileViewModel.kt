package com.example.italikachallenges.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.italikachallenges.models.People
import com.example.italikachallenges.ui.profile.adapters.MoviesAdapter

class ProfileViewModel : ViewModel() {

    private val profileInteractor by lazy { ProfileInteractor() }

    private val _people: MutableLiveData<People> by lazy { MutableLiveData() }
    val people: LiveData<People> = _people

    fun getPoularPeople() {
        profileInteractor.getPopularPeople { peopleResult ->
            if (peopleResult.isNotEmpty()) {
                _people.postValue(peopleResult[0])
            }
        }
    }

    fun  getAdapter():MoviesAdapter{
       return MoviesAdapter(_people.value?.movies ?: emptyList())
    }
    public override fun onCleared() {
        super.onCleared()
    }
}