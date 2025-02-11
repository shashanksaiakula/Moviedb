package com.example.moviedb.data.remote.model

data class PersonDetails(
    val id: Int,
    val name: String,
    val biography: String,
    val profile_path: String?,
    val birthday: String?,
    val place_of_birth: String?,
    val known_for_department: String
)

