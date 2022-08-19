package com.example.italikachallenges.ui.gallery

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.italikachallenges.models.Image
import com.example.italikachallenges.utis.FirebaseConstants
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class GalleryViewModel : ViewModel() {

    private val galeryInteractor by lazy {
        GalleryInteractor()
    }

    private val _images: MutableLiveData<List<Image>> by lazy { MutableLiveData() }
    val images = _images


    fun getImages() {
        galeryInteractor.getImages { imagesResponse ->
            _images.postValue(imagesResponse)
        }
    }
    fun uploadImages(filePath: Uri?){
        galeryInteractor.uploadImage(filePath) { successUpload ->
            if (successUpload) {
                getImages()
            } else {
                //Message error
            }

        }
    }

    public override fun onCleared() {
        super.onCleared()
    }
}