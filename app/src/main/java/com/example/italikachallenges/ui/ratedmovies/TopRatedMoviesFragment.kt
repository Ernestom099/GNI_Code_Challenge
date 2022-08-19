package com.example.italikachallenges.ui.ratedmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.italikachallenges.R
import com.example.italikachallenges.databinding.FragmentMostCalificatedMoviesBinding
import com.example.italikachallenges.ui.dialogs.GenericErrorDialog
import com.example.italikachallenges.ui.movies.MoviesFragmentDirections
import com.example.italikachallenges.ui.popularmovies.adapters.GridMoviesAdapter

class TopRatedMoviesFragment : Fragment() {

    private var _binding: FragmentMostCalificatedMoviesBinding? = null
    private val binding get() = _binding!!
    private val ratedMoviesViewModel: RatedMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMostCalificatedMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ratedMoviesViewModel.movies.observe(viewLifecycleOwner, Observer { moviesResponse ->
            if (moviesResponse.success) {
                binding.recyclerTopRatedMovies.adapter = GridMoviesAdapter(moviesResponse.movies) { movie ->
                    val action = MoviesFragmentDirections.moviesToRecommendations(movie.id)
                    findNavController().navigate(action)
                }
            } else {
                val dialog = GenericErrorDialog()
                dialog.arguments =
                    GenericErrorDialog.createBundle(getString(R.string.error_retrieving_message))
                dialog.show(
                    childFragmentManager, GenericErrorDialog.TAG
                )
            }
        })

        ratedMoviesViewModel.getPopularMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ratedMoviesViewModel.onCleared()
        _binding = null
    }
}