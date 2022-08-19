package com.example.italikachallenges.ui.map

import android.util.Log
import com.example.italikachallenges.models.Location
import com.example.italikachallenges.utis.FirebaseConstants
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MapsInteractor {
    fun getLocations(locationCallback: (List<Location>) -> Unit) {
        val locations: MutableList<Location> = mutableListOf()

        Firebase.firestore
            .collection(FirebaseConstants.TABLE_LOCATION)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    locations.add(document.toObject())
                }
                locationCallback(locations)
            }
            .addOnFailureListener { exception ->
                Log.w("ITERATOR", "Error getting documents.", exception)
                locationCallback(emptyList())
            }
    }
}