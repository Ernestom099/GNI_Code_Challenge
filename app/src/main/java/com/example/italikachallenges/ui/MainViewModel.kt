package com.example.italikachallenges.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.italikachallenges.utis.FirebaseConstants
import com.google.android.gms.location.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {


    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private var currentLocation: Location? = null

    fun addValueToFirestore(context: Context) {
        var id = 1

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        locationRequest = LocationRequest.create().apply {
            interval = DELAY_TIME
            fastestInterval = DELAY_TIME
            maxWaitTime = DELAY_TIME
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }


        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                currentLocation = locationResult.lastLocation
            }
        }

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
             return
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )

        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                delay(DELAY_TIME)
                currentLocation?.let {
                    Log.e("LOCATION UPDATED:", "LOCATION UPDATED")
                    Firebase.firestore.collection(FirebaseConstants.TABLE_LOCATION)
                        .add(com.example.italikachallenges.models.Location(it.latitude,it.longitude))

                }
            }
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
        private const val SEG = 1000L
        private const val MIN = 60 * SEG
        private const val DELAY_TIME = 5 * MIN
    }
}