package com.example.italikachallenges.services

import com.example.italikachallenges.models.PopularPeopleResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleService {

    @GET("person/popular")
    suspend fun getPopularPeople(@Query("api_key") api_key: String): PopularPeopleResponse

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        fun create(): PeopleService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(PeopleService::class.java)
        }
    }
}