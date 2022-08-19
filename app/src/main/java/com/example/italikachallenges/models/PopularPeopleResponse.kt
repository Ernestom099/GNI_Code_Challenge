package com.example.italikachallenges.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PopularPeopleResponse(
    @SerializedName("page")
    @Expose
    val page: Int,
    @SerializedName("results")
    @Expose
    val results: List<People>,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int,
    @SerializedName("total_results")
    @Expose
    val totalResults: Int,
)