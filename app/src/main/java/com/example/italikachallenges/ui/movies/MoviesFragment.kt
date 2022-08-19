package com.example.italikachallenges.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.italikachallenges.databinding.FragmentMoviesBinding
import com.example.italikachallenges.ui.movies.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            viewPager.adapter = ViewPagerAdapter(childFragmentManager)

            viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout))


            tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager.currentItem = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}