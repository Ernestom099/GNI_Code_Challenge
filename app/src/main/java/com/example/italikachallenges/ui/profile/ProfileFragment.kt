package com.example.italikachallenges.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.italikachallenges.R
import com.example.italikachallenges.databinding.FragmentProfileBinding
import com.example.italikachallenges.ui.dialogs.GenericErrorDialog
import com.example.italikachallenges.ui.profile.adapters.MoviesAdapter
import com.example.italikachallenges.utis.Const

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.people.observe(viewLifecycleOwner, Observer { people ->

            with(binding) {
              if(people != null) {
                  Glide.with(requireContext())
                      .load(Const.IMAGE_URL + people.profilePath)
                      .circleCrop().circleCrop()
                      .into(imageProfilePhoto)

                  textName.text = people.name
                  recyclerMovies.layoutManager =
                      LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                  recyclerMovies.adapter = profileViewModel.getAdapter()
              }else{
                  val dialog = GenericErrorDialog()
                  dialog.arguments  = GenericErrorDialog.createBundle(getString(R.string.error_retrieving_message))
                  dialog.show(
                      childFragmentManager, GenericErrorDialog.TAG)
              }
            }
        })

        profileViewModel.getPopularPeople()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        profileViewModel.onCleared()
        _binding = null
    }
}