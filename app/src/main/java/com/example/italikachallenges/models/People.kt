package com.example.italikachallenges.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class People(

    @SerializedName("adult")
    @Expose
    val adult: Boolean,
    @SerializedName("gender")
    @Expose
    val gender: Int,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("known_for")
    @Expose
    val movies: List<Movie>,
    @SerializedName("known_for_department")
    @Expose
    val knownForDepartment: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("popularity")
    @Expose
    val popularity: Double,
    @SerializedName("profile_path")
    @Expose
    val profilePath: String
)
