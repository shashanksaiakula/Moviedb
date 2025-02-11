package com.example.moviedb.data.remote.model

data class Person(
    val id: Int,
    val name: String,
    val profile_path: String?,
    val popularity : Double?,
    val known_for_department : String?
)