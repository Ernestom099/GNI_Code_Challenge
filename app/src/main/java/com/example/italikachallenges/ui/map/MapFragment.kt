package com.example.italikachallenges.ui.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.italikachallenges.R
import com.example.italikachallenges.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private lateinit var mMap: GoogleMap

    private val mapViewModel: MapViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMap(savedInstanceState)
    }

    private fun setupObservers() {

        mapViewModel.locations.observe(viewLifecycleOwner, Observer { locations ->
            mMap.clear()

            var latLng = LatLng(0.0, 0.0)

            for (location in locations) {
                latLng = LatLng(location.latitude, location.longitude)
                mMap.addMarker(MarkerOptions().position(latLng).title(location.date.toString()))
            }

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
        })

        mapViewModel.getLocations()
    }


    private fun setupMap(savedInstanceState: Bundle?) {
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.onResume()
        try {
            MapsInitializer.initialize(requireContext())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        binding.mapView.getMapAsync(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        setupObservers()
    }

}