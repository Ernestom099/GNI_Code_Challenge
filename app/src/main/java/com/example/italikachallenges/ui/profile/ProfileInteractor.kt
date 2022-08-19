package com.example.italikachallenges.ui.profile

import android.util.Log
import com.example.italikachallenges.injections.DaggerApplicationComponent
import com.example.italikachallenges.models.People
import com.example.italikachallenges.services.MoviesService
import com.example.italikachallenges.services.PeopleService
import com.example.italikachallenges.utis.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileInteractor @Inject constructor() {

    init {
        DaggerApplicationComponent.create().inject(this)
    }

    @Inject
    lateinit var peopleService: PeopleService

    fun getPopularPeople(popularPeopleCallback: (List<People>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = peopleService.getPopularPeople(Const.API_KEY_V3)
                popularPeopleCallback(result.results)
            } catch (e: Exception) {
                 popularPeopleCallback(null)
            }
        }
    }
}