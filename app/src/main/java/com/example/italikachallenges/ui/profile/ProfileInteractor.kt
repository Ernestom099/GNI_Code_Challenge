package com.example.italikachallenges.ui.profile

import android.util.Log
import com.example.italikachallenges.models.People
import com.example.italikachallenges.services.PeopleService
import com.example.italikachallenges.utis.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileInteractor {
    fun getPopularPeople(popularPeopleCallback: (List<People>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = PeopleService.create().getPopularPeople(Const.API_KEY_V3)
                popularPeopleCallback(result.results)
            } catch (e: Exception) {
                 popularPeopleCallback(emptyList())
            }
        }
    }
}