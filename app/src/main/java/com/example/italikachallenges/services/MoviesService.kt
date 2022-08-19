package com.example.italikachallenges.services

import com.example.italikachallenges.models.MoviesResponse
import com.example.italikachallenges.models.PopularPeopleResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") api_key: String): MoviesResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") api_key: String): MoviesResponse

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecomendations(
        @Path(value = "movie_id", encoded = true) movieId: Int,
        @Query("api_key") api_key: String
    ): MoviesResponse

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        fun create(): MoviesService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(MoviesService::class.java)
        }
    }
}