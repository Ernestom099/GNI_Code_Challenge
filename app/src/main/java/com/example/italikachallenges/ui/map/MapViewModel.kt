package com.example.italikachallenges.ui.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.italikachallenges.models.Location

class MapViewModel : ViewModel() {

    private val mapsInteractor: MapsInteractor by lazy {
        MapsInteractor()
    }

    private val _locations: MutableLiveData<List<Location>> by lazy { MutableLiveData() }
    val locations = _locations

    fun getLocations() {
        mapsInteractor.getLocations { locations ->
            _locations.postValue(locations)
        }
    }

}