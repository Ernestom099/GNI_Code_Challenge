package com.example.italikachallenges.ui

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.italikachallenges.R
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
    private var id = 1

    fun startUpdatingLocation(context: Context) {


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
                        .add(
                            com.example.italikachallenges.models.Location(
                                it.latitude,
                                it.longitude
                            )
                        )
                    showNotification(context, id)
                    id += 1
                }
            }
        }
    }


    private fun showNotification(context: Context, id: Int) {
        createNotificationChannel(context)
        var builder = NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications_black_24dp)
            .setContentTitle(context.getString(R.string.location))
            .setContentText(context.getString(R.string.has_been_sent))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            notify(id, builder.build())
        }
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(MainActivity.CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
        private const val SEG = 1000L
        private const val MIN = 60 * SEG
        private const val DELAY_TIME = 5 * MIN
    }
}