package com.example.italikachallenges.ui.gallery

import android.net.Uri
import android.util.Log
import com.example.italikachallenges.models.Image
import com.example.italikachallenges.models.Location
import com.example.italikachallenges.utis.FirebaseConstants
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class GalleryInteractor {

    fun getImages(imagesCallback: (List<Image>?) -> Unit) {
        val images: MutableList<Image> = mutableListOf()

        Firebase.firestore
            .collection(FirebaseConstants.TABLE_IMAGES)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    images.add(document.toObject())
                }
                imagesCallback(images)
            }
            .addOnFailureListener { exception ->
                Log.w("ITERATOR", "Error getting documents.", exception)
                imagesCallback(emptyList())
            }
    }

    fun uploadImage(filePath: Uri?, successCallback: (Boolean) -> Unit) {
        val ref = FirebaseStorage.getInstance().reference.child(UUID.randomUUID().toString())
        ref.putFile(filePath!!)
            .addOnSuccessListener { task ->
                task.storage.downloadUrl.addOnSuccessListener { uri ->
                    val image = Image(uri.toString())
                    Firebase.firestore.collection(FirebaseConstants.TABLE_IMAGES)
                        .add(image)
                        .addOnSuccessListener {
                            successCallback(true)
                        }
                        .addOnFailureListener {
                            successCallback(false)
                        }
                }
                    .addOnFailureListener {
                    successCallback(false)
                }
            }
            .addOnFailureListener {
                successCallback(false)
            }
    }
}