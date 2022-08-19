package com.example.italikachallenges.ui.gallery

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.italikachallenges.databinding.FragmentGalleryBinding
import com.example.italikachallenges.models.Image
import com.example.italikachallenges.ui.popularmovies.adapters.GridGalleryAdapter
import com.example.italikachallenges.utis.FirebaseConstants
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.IOException
import java.util.*


class GalleryFragment : Fragment() {

    private val PICK_IMAGE_REQUEST = 71

    private val galleryViewModel: GalleryViewModel by viewModels()
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        galleryViewModel.images.observe(viewLifecycleOwner, Observer { images ->
            binding.recyclerGallery.adapter = GridGalleryAdapter(images)
        })
        galleryViewModel.getImages()

        setuplisteners()
    }

    fun setuplisteners() {
        binding.btnSelectImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                PICK_IMAGE_REQUEST
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }
            if (data.data != null) {
                galleryViewModel.uploadImages(data.data)
            } else {
                Toast.makeText(requireContext(), "Please Upload an Image", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        galleryViewModel.onCleared()
        _binding = null
    }

}