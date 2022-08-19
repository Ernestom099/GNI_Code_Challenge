package com.example.italikachallenges.injections

import com.example.italikachallenges.ui.popularmovies.PopularMoviesInteractor
import com.example.italikachallenges.ui.popularmovies.PopularMoviesViewModel
import com.example.italikachallenges.ui.profile.ProfileInteractor
import com.example.italikachallenges.ui.profile.ProfileViewModel
import com.example.italikachallenges.ui.ratedmovies.RatedMoviesInteractor
import com.example.italikachallenges.ui.ratedmovies.RatedMoviesViewModel
import com.example.italikachallenges.ui.recommendations.RecommendationsInteractor
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(viewModel: ProfileViewModel)
    fun inject(viewModel: RatedMoviesViewModel)
    fun inject(viewModel: PopularMoviesViewModel)

    fun inject(interactor: PopularMoviesInteractor)
    fun inject(interactor: RecommendationsInteractor)
    fun inject(interactor: RatedMoviesInteractor)
    fun inject(interactor: ProfileInteractor)
}