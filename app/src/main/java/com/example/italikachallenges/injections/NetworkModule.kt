package com.example.italikachallenges.injections

import com.example.italikachallenges.services.MoviesService
import com.example.italikachallenges.services.PeopleService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideMoviesRetrofitService(): MoviesService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MoviesService.BASE_URL)
            .build()
            .create(MoviesService::class.java)
    }
    @Singleton
    @Provides
    fun providePeopleRetrofitService(): PeopleService {
        return  Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(PeopleService.BASE_URL)
                .build()
            .create(PeopleService::class.java)
        }
}