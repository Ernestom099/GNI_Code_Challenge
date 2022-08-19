package com.example.italikachallenges.ui.popularmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.italikachallenges.databinding.FragmentMostPopularBinding
import com.example.italikachallenges.ui.movies.MoviesFragmentDirections
import com.example.italikachallenges.ui.popularmovies.adapters.GridMoviesAdapter

class MostPopularMoviesFragment : Fragment() {

    private var _binding: FragmentMostPopularBinding? = null
    private val binding get() = _binding!!

    private val popularMoviesViewModel: PopularMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMostPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularMoviesViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            binding.recyclerPopularMovies.adapter = GridMoviesAdapter(movies) { movie ->
                val action = MoviesFragmentDirections.moviesToRecommendations(movie.id)
                findNavController().navigate(action)
            }
        })

        popularMoviesViewModel.getPopularMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        popularMoviesViewModel.onCleared()
        _binding = null
    }
}