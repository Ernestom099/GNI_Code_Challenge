package com.example.italikachallenges.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.italikachallenges.injections.ApplicationComponent
import com.example.italikachallenges.injections.DaggerApplicationComponent
import com.example.italikachallenges.models.People
import com.example.italikachallenges.ui.profile.adapters.MoviesAdapter
import javax.inject.Inject


class ProfileViewModel : ViewModel() {
    init {
        DaggerApplicationComponent.create().inject(this)
    }

    @Inject
    lateinit var profileInteractor: ProfileInteractor


    private val _people: MutableLiveData<People> by lazy { MutableLiveData() }
    val people: LiveData<People> = _people

    fun getPopularPeople() {
        profileInteractor.getPopularPeople { peopleResult ->
            if (peopleResult?.isNotEmpty() == true) {
                _people.postValue(peopleResult!![0])
            } else {
                _people.postValue(null)
            }
        }
    }

    fun getAdapter(): MoviesAdapter {
        return MoviesAdapter(_people.value?.movies ?: emptyList())
    }

    public override fun onCleared() {
        super.onCleared()
    }
}