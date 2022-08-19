package com.example.italikachallenges.ui.recommendations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.italikachallenges.databinding.FragmentBestRecommendationsBinding

class BestRecommendationsFragment : Fragment() {

    private var _binding: FragmentBestRecommendationsBinding? = null
    private val binding get() = _binding!!
    private val recommendationsViewModel: RecommendationsViewModel by viewModels()
    private val args:BestRecommendationsFragmentArgs  by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBestRecommendationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recommendationsViewModel.movies.observe(viewLifecycleOwner, Observer {
            binding.recyclerRecommendations.adapter = recommendationsViewModel.getAdapter()
        })

        recommendationsViewModel.getPopularMovies(args.movieId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}